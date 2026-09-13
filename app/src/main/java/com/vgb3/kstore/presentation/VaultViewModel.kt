package com.vgb3.kstore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vgb3.kstore.domain.interactor.VaultInteractor
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.vgb3.kstore.KStoreApplication
import com.vgb3.kstore.presentation.model.VaultResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VaultViewModel(
    private val vaultInteractor: VaultInteractor
) : ViewModel() {
    private val _state = MutableStateFlow<VaultResult>(VaultResult.Idle)
    val vaultState = _state.asStateFlow()

    init {
        getVaultItems()
    }

    fun getVaultItems() {
        viewModelScope.launch {
            vaultInteractor
                .getVaultSummaryItems()
                .collect { vaultItems ->
                    _state.value = VaultResult.VaultContent(
                        vaultList = vaultItems
                    )
                }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val vaultInteractor = (this[APPLICATION_KEY] as KStoreApplication).diContainer.provideVaultInteractor()
                VaultViewModel(vaultInteractor)
            }
        }
    }
}