package com.vgb3.kstore.domain.repository.settings

import com.vgb3.kstore.domain.model.settings.ThemeMode
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val themeMode: Flow<ThemeMode>
    suspend fun setTheme(mode: ThemeMode)
}