package com.vgb3.kstore.domain.usecase.settings

import com.vgb3.kstore.domain.model.settings.ThemeMode
import com.vgb3.kstore.domain.repository.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetSettingsThemeUseCase(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<ThemeMode> = repository.themeMode
}