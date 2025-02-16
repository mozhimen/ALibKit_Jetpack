package com.mozhimen.libk.jetpack.datastore.utils

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.mozhimen.kotlin.elemk.commons.ISuspendA_BListener
import com.mozhimen.kotlin.elemk.commons.ISuspendA_Listener
import com.mozhimen.kotlin.utilk.android.util.UtilKLogWrapper
import com.mozhimen.kotlin.utilk.android.util.UtilKLongLogWrapper
import com.mozhimen.kotlin.utilk.commons.IUtilK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * @ClassName DataStoreWrapperUtil
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/2/13
 * @Version 1.0
 */
inline fun <T> DataStore<Preferences>.dataMapSafe(
    default: T,
    crossinline transform: ISuspendA_BListener<Preferences, T>,
): Flow<T> =
    DataStoreWrapperUtil.dataMapSafe(this, default, transform)

suspend fun DataStore<Preferences>.editSafe(
    transform: ISuspendA_Listener<MutablePreferences>,
) {
    DataStoreWrapperUtil.editSafe(this, transform)
}

/////////////////////////////////////////////////////////////////////

object DataStoreWrapperUtil : IUtilK {
    /**
     * Wraps DataStore operations with corruption handling
     */
    inline fun <T> dataMapSafe(
        dataStore: DataStore<Preferences>,
        default: T,
        crossinline transform: ISuspendA_BListener<Preferences, T>,
    ): Flow<T> =
        dataStore.data
            .catch { exception ->
                when (exception) {
                    is CorruptionException -> {
                        UtilKLogWrapper.e(TAG, "Corruption in preferences, recreating store", exception)
                        emit(dataStore.applyUpdateDataEmpty())// If corruption is detected, clear all data
                    }

                    is IOException -> {
                        UtilKLogWrapper.e(TAG, "safeRead: Error reading preferences", exception)
                        emit(dataStore.applyUpdateData())
                    }

                    else -> {
                        throw exception
                    }
                }
            }
            .catch { exception ->
                UtilKLogWrapper.e(TAG, "Fallback: returning default value", exception)
                emit(dataStore.applyUpdateDataEmpty())// Fallback in case the updateData above also fails
            }
            .catch {
                // Final fallback - return default value
                emit(emptyPreferences())
            }.map { preferences ->
                try {
                    transform.invoke(preferences)
                } catch (e: Exception) {
                    UtilKLogWrapper.e("Error mapping preferences", e)
                    default
                }
            }

    /**
     * Wraps DataStore write operations with error handling
     */
    suspend fun editSafe(
        dataStore: DataStore<Preferences>,
        transform: ISuspendA_Listener<MutablePreferences>,
    ) {
        try {
            dataStore.edit(transform)
        } catch (e: Exception) {
            UtilKLongLogWrapper.e("Error writing to preferences", e)
            if (e is CorruptionException) {// Attempt to recreate store if corrupt
                dataStore.edit { preferences ->
                    // Clear and retry the update
                    preferences.clear()
                    transform(preferences)
                }
            }
        }
    }
}