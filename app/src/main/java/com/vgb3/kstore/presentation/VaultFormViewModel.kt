package com.vgb3.kstore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vgb3.kstore.KStoreApplication
import com.vgb3.kstore.domain.interactor.VaultInteractor
import com.vgb3.kstore.domain.model.input.CreateVaultItem
import com.vgb3.kstore.domain.usecase.GetCategoriesUseCase
import com.vgb3.kstore.presentation.map.toUi
import com.vgb3.kstore.presentation.model.VaultFormInput
import com.vgb3.kstore.presentation.model.VaultFormInputs
import com.vgb3.kstore.presentation.model.VaultFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VaultFormViewModel(
    private val vaultInteractor: VaultInteractor,
    private val getCategories: GetCategoriesUseCase
) : ViewModel() {

    private val input = MutableStateFlow(VaultFormInput())
    private val isSaving = MutableStateFlow(false)

    val uiState = combine(
        input,
        getCategories(),
        isSaving
    ) { input, categories, saving ->
        VaultFormUiState(
            input = input,
            categories = categories.map { it.toUi() },
            isSaving = saving,
            canSave = input.appNameInput.isNotBlank() && input.passwordInput.isNotBlank() && !saving
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = VaultFormUiState()
    )

    init {
        viewModelScope.launch {
            getCategories()
                .stateIn(viewModelScope)
        }
    }

    fun onInput(inputField: VaultFormInputs, fieldValue: String) {
        input.update { currentState ->
            when (inputField) {
                VaultFormInputs.APP_NAME_INPUT -> currentState.copy(appNameInput = fieldValue)
                VaultFormInputs.URL_INPUT -> currentState.copy(urlInput = fieldValue)
                VaultFormInputs.LOGIN_INPUT -> currentState.copy(loginInput = fieldValue)
                VaultFormInputs.PASSWORD_INPUT -> currentState.copy(passwordInput = fieldValue)
            }
        }
    }

    fun onCategorySelected(categoryId: Long?) {
        input.update { it.copy(selectedCategoryId = categoryId) }
    }

    fun createVault() {
        val curState = input.value
        viewModelScope.launch {
            isSaving.value = true
            vaultInteractor.createVaultItem(
                CreateVaultItem.Login(
                    title = curState.appNameInput,
                    url = curState.urlInput,
                    login = curState.loginInput,
                    password = curState.passwordInput,
                    categoryId = curState.selectedCategoryId
                )
            )
            input.value = VaultFormInput()
            isSaving.value = false
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val vaultInteractor = (this[APPLICATION_KEY] as KStoreApplication).diContainer.provideVaultInteractor()
                val getCategories = (this[APPLICATION_KEY] as KStoreApplication).diContainer.provideGetCategoriesUseCase()
                VaultFormViewModel(vaultInteractor, getCategories)
            }
        }
    }
}