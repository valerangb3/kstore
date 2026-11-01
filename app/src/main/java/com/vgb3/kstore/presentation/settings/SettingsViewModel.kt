package com.vgb3.kstore.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vgb3.kstore.KStoreApplication
import com.vgb3.kstore.domain.model.settings.ThemeMode
import com.vgb3.kstore.domain.repository.settings.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
): ViewModel() {

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val settingsRepository = (this[APPLICATION_KEY] as KStoreApplication).diContainer.provideSettingsRepository()
                SettingsViewModel(
                    settingsRepository
                )
            }
        }
    }

    fun onThemeSelected(themeMode: ThemeMode) {
        viewModelScope.launch { settingsRepository.setTheme(themeMode) }
    }
}