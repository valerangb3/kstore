package com.vgb3.kstore.data.repository.settings

import com.vgb3.kstore.data.datasource.local.settings.SettingsLocalDataSource
import com.vgb3.kstore.domain.model.settings.ThemeMode
import com.vgb3.kstore.domain.repository.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val localDataSource: SettingsLocalDataSource
): SettingsRepository {
    override val themeMode: Flow<ThemeMode> = localDataSource.themeKey.map { toThemeMode(it) }

    override suspend fun setTheme(mode: ThemeMode) {
        localDataSource.setThemeKey(toKey(mode))
    }

    private fun toKey(themeMode: ThemeMode): String {
        return when (themeMode) {
            ThemeMode.LIGHT -> "light"
            ThemeMode.DARK -> "dark"
            ThemeMode.SYSTEM -> "system"
        }
    }

    private fun toThemeMode(themeKey: String?): ThemeMode {
        return when (themeKey) {
            "light" -> ThemeMode.LIGHT
            "dark" -> ThemeMode.DARK
            else -> ThemeMode.SYSTEM
        }
    }
}