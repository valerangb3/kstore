package com.vgb3.kstore.data.datasource.local.settings

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class SettingsDataStoreDataSource(
    private val dataStore: DataStore<Preferences>
): SettingsLocalDataSource {

    private val themeModeKey = stringPreferencesKey("theme_mode")

    override val themeKey: Flow<String?> = dataStore.data
        .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
        .map { prefs -> prefs[themeModeKey] }

    override suspend fun setThemeKey(theme: String) {
        dataStore.edit { prefs -> prefs[themeModeKey] = theme }
    }
}