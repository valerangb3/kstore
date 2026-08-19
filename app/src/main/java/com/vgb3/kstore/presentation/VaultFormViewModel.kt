package com.vgb3.kstore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vgb3.kstore.KStoreApplication
import com.vgb3.kstore.domain.interactor.VaultInteractor
import com.vgb3.kstore.domain.model.input.CreateVaultItem
import com.vgb3.kstore.presentation.model.VaultFormInputs
import com.vgb3.kstore.presentation.model.VaultFormResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VaultFormViewModel(
    private val vaultInteractor: VaultInteractor
) : ViewModel() {
    private val _state = MutableStateFlow<VaultFormResult>(VaultFormResult.VaultFormFields())
    val vaultFormState = _state.asStateFlow()

    private fun updateFormState(inputField: VaultFormInputs, fieldValue: String) {
        _state.update { currentState ->
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
                clearFields()
            }
        }
    }

    fun onInput(inputField: VaultFormInputs, fieldValue: String) = updateFormState(inputField, fieldValue)

    fun clearFields() {
        _state.value = VaultFormResult.VaultFormFields()
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val vaultInteractor = (this[APPLICATION_KEY] as KStoreApplication).diContainer.provideVaultInteractor()
                VaultFormViewModel(vaultInteractor)
            }
        }
    }
}