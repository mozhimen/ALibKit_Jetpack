package com.mozhimen.libk.jetpack.datastore.utils

import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
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
    crossinline transform: suspend (a: Preferences) -> T,/*ISuspendA_BListener<Preferences, T>*/
): Flow<T> =
    DataStoreWrapperUtil.dataMapSafe(this, default, transform)

suspend fun DataStore<Preferences>.editSafe(
    transform: suspend (a: MutablePreferences) -> Unit/*ISuspendA_Listener<MutablePreferences>*/
) {
    DataStoreWrapperUtil.editSafe(this, transform)
}

/////////////////////////////////////////////////////////////////////

object DataStoreWrapperUtil {
    val NAME: String
        get() = synchronized(this) { this.javaClass.simpleName }
    val TAG: String
        get() = "$NAME>>>>>"

    /**
     * Wraps DataStore operations with corruption handling
     */
    inline fun <T> dataMapSafe(
        dataStore: DataStore<Preferences>,
        default: T,
        crossinline transform: suspend (a: Preferences) -> T,
    ): Flow<T> =
        dataStore.data
            .catch { exception ->
                when (exception) {
                    is CorruptionException -> {
                        Log.e(TAG, "Corruption in preferences, recreating store", exception)
                        emit(dataStore.applyUpdateDataEmpty())// If corruption is detected, clear all data
                    }

                    is IOException -> {
                        Log.e(TAG, "safeRead: Error reading preferences", exception)
                        emit(dataStore.applyUpdateData())
                    }

                    else -> {
                        throw exception
                    }
                }
            }
            .catch { exception ->
                Log.e(TAG, "Fallback: returning default value", exception)
                emit(dataStore.applyUpdateDataEmpty())// Fallback in case the updateData above also fails
            }
            .catch {
                // Final fallback - return default value
                emit(emptyPreferences())
            }.map { preferences ->
                try {
                    transform.invoke(preferences)
                } catch (e: Exception) {
                    Log.e(TAG, "Error mapping preferences", e)
                    default
                }
            }

    /**
     * Wraps DataStore write operations with error handling
     */
    suspend fun editSafe(
        dataStore: DataStore<Preferences>,
        transform: suspend (a: MutablePreferences) -> Unit,
    ) {
        try {
            dataStore.edit(transform)
        } catch (e: Exception) {
            Log.e(TAG,"Error writing to preferences", e)
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