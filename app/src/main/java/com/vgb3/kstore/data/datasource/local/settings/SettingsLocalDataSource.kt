package com.vgb3.kstore.data.datasource.local.settings

import kotlinx.coroutines.flow.Flow

interface SettingsLocalDataSource {
    val themeKey: Flow<String?>
    suspend fun setThemeKey(theme: String)
}