package com.vgb3.kstore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vgb3.kstore.domain.interactor.VaultInteractor
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.vgb3.kstore.KStoreApplication
import com.vgb3.kstore.domain.model.input.CreateVaultItem
import com.vgb3.kstore.presentation.model.VaultFormInputs
import com.vgb3.kstore.presentation.model.VaultFormResult
import com.vgb3.kstore.presentation.model.VaultResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VaultViewModel(
    private val vaultInteractor: VaultInteractor
) : ViewModel() {
    private val _state = MutableStateFlow<VaultResult>(VaultResult.Idle)
    val vaultState = _state.asStateFlow()

    private val _formState = MutableStateFlow<VaultFormResult>(VaultFormResult.VaultFormFields())
    val vaultFormState = _formState.asStateFlow()

    init {
        getVaultItems()
    }

    private fun updateFormState(inputField: VaultFormInputs, fieldValue: String) {
        _formState.update { currentState ->
            if (currentState is VaultFormResult.VaultFormFields) {
                when (inputField) {
                    VaultFormInputs.APP_NAME_INPUT -> {
                        currentState.copy(
                            appNameInput = fieldValue
                        )
                    }
                    VaultFormInputs.URL_INPUT -> {
                        currentState.copy(
                            urlInput = fieldValue
                        )
                    }
                    VaultFormInputs.LOGIN_INPUT -> {
                        currentState.copy(
                            loginInput = fieldValue
                        )
                    }
                    VaultFormInputs.PASSWORD_INPUT -> {
                        currentState.copy(
                            passwordInput = fieldValue
                        )
                    }
                }
            } else {
                when (inputField) {
                    VaultFormInputs.APP_NAME_INPUT -> {
                        VaultFormResult.VaultFormFields(
                            appNameInput = fieldValue
                        )
                    }
                    VaultFormInputs.URL_INPUT -> {
                        VaultFormResult.VaultFormFields(
                            urlInput = fieldValue
                        )
                    }
                    VaultFormInputs.LOGIN_INPUT -> {
                        VaultFormResult.VaultFormFields(
                            loginInput = fieldValue
                        )
                    }
                    VaultFormInputs.PASSWORD_INPUT -> {
                        VaultFormResult.VaultFormFields(
                            passwordInput = fieldValue
                        )
                    }
                }

            }
        }
    }

    fun createVault() {
        val curState = vaultFormState.value
        if (curState is VaultFormResult.VaultFormFields) {
            viewModelScope.launch {
                vaultInteractor.createVaultItem(
                    CreateVaultItem.Login(
                        title = curState.appNameInput,
                        url = curState.urlInput,
                        login = curState.loginInput,
                        password = curState.passwordInput,
                        categoryId = 0
                    )
                )
                clearFormFields()
                getVaultItems()
            }
        }
    }

    fun onInput(inputField: VaultFormInputs, fieldValue: String) = updateFormState(inputField, fieldValue)

    fun clearFormFields() {
        _formState.value = VaultFormResult.VaultFormFields()
    }

    fun getVaultItems() {
        viewModelScope.launch {
            vaultInteractor
                .getVaultItems()
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