package com.mozhimen.libk.jetpack.datastore.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.mozhimen.kotlin.elemk.commons.ISuspendABC_Listener
import com.mozhimen.kotlin.elemk.commons.ISuspendA_Listener

/**
 * @ClassName UtilKPreferences
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/12/7 0:22
 * @Version 1.0
 */
suspend fun <T> DataStore<Preferences>.applyUpdateData(key: String, value: T, transform: ISuspendABC_Listener<String, T, MutablePreferences>): Preferences =
    PreferencesUtil.applyUpdateData(this, key, value, transform)

suspend fun DataStore<Preferences>.applyUpdateData(): Preferences =
    PreferencesUtil.applyUpdateData(this)

suspend fun DataStore<Preferences>.applyUpdateDataEmpty(): Preferences =
    PreferencesUtil.applyUpdateDataEmpty(this)

//////////////////////////////////////////////////////////////////////////

/**
 * updateData：更安全、更简洁，适合大多数场景，尤其是在并发环境下。
 * edit：更灵活，适合需要手动控制数据更新的场景。
 */
object PreferencesUtil {
    @JvmStatic
    suspend fun <T> applyUpdateData(dataStore: DataStore<Preferences>, key: String, value: T, transform: ISuspendABC_Listener<String, T, MutablePreferences>): Preferences =
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply { transform.invoke(key, value, this) }
        }

    @JvmStatic
    suspend fun applyUpdateData(dataStore: DataStore<Preferences>): Preferences =
        dataStore.updateData { preferences ->
            preferences
        }

    @JvmStatic
    suspend fun applyUpdateDataEmpty(dataStore: DataStore<Preferences>): Preferences =
        dataStore.updateData {
            emptyPreferences()
        }
}