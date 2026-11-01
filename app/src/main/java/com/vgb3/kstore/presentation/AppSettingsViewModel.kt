package com.vgb3.kstore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vgb3.kstore.KStoreApplication
import com.vgb3.kstore.domain.model.settings.ThemeMode
import com.vgb3.kstore.domain.usecase.settings.GetSettingsThemeUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class AppSettingsViewModel(getSettingsTheme: GetSettingsThemeUseCase): ViewModel() {
    val themeMode = getSettingsTheme()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ThemeMode.SYSTEM
        )


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val settingsThemeUseCase = (this[APPLICATION_KEY] as KStoreApplication).diContainer.provideGetSettingsThemeUseCase()
                AppSettingsViewModel(settingsThemeUseCase)
            }
        }
    }
}