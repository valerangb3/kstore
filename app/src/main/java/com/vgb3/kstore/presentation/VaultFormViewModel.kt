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
import com.vgb3.kstore.presentation.model.RecordForm
import com.vgb3.kstore.presentation.model.VaultFormInputs
import com.vgb3.kstore.presentation.model.VaultFormUiState
import com.vgb3.kstore.presentation.ui.model.RecordFormType
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

    private val formState = MutableStateFlow<RecordForm?>(null)
    private val isSaving = MutableStateFlow(false)

    val uiState = combine(
        formState,
        getCategories(),
        isSaving
    ) { form, categories, saving ->
        VaultFormUiState(
            form = form,
            categories = categories.map { it.toUi() },
            isSaving = saving,
            canSave = !saving &&
                form is RecordForm.Login &&
                form.appNameInput.isNotBlank() &&
                form.passwordInput.isNotBlank()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = VaultFormUiState()
    )

    init {
        //todo по идее нигде не используется
        viewModelScope.launch {
            getCategories()
                .stateIn(viewModelScope)
        }
    }

    fun start(formType: RecordFormType) {
        when (formType) {
            RecordFormType.LOGIN -> formState.value = RecordForm.Login()
            RecordFormType.BANK_CARD -> formState.value = RecordForm.BankCard()
            RecordFormType.SECURE_NOTE -> formState.value = RecordForm.SecureNote()
        }
    }

    fun onInput(inputField: VaultFormInputs, fieldValue: String) {
        formState.update { currentFormState ->
            currentFormState?.let { state ->
                when (state) {
                    is RecordForm.Login -> {
                        val field = inputField as? VaultFormInputs.Login ?: return@update state
                        when (field) {
                            VaultFormInputs.Login.APP_NAME_INPUT -> state.copy(appNameInput = fieldValue)
                            VaultFormInputs.Login.URL_INPUT -> state.copy(urlInput = fieldValue)
                            VaultFormInputs.Login.LOGIN_INPUT -> state.copy(loginInput = fieldValue)
                            VaultFormInputs.Login.PASSWORD_INPUT -> state.copy(passwordInput = fieldValue)
                        }
                    }
                    is RecordForm.BankCard -> {
                        val field = inputField as? VaultFormInputs.BankCard ?: return@update state
                        when (field) {
                            VaultFormInputs.BankCard.TITLE -> state.copy(cardTitle = fieldValue)
                            VaultFormInputs.BankCard.NUMBER -> state.copy(cardNumber = fieldValue)
                            VaultFormInputs.BankCard.EXPIRY -> state.copy(cardExpiry = fieldValue)
                            VaultFormInputs.BankCard.CVV -> state.copy(cardCvv = fieldValue)
                            VaultFormInputs.BankCard.HOLDER -> state.copy(cardHolder = fieldValue)
                        }
                    }
                    is RecordForm.SecureNote -> {
                        val field = inputField as? VaultFormInputs.SecureNote ?: return@update state
                        when (field) {
                            VaultFormInputs.SecureNote.TITLE -> state.copy(secureTitle = fieldValue)
                            VaultFormInputs.SecureNote.TEXT -> state.copy(secureText = fieldValue)
                        }
                    }
                }
            }
        }
    }

    fun onCategorySelected(categoryId: Long?) {
        formState.update { currentFormState ->
            currentFormState?.let { state ->
                when (state) {
                    is RecordForm.Login -> state.copy(selectedCategoryId = categoryId)
                    is RecordForm.BankCard -> TODO()
                    is RecordForm.SecureNote -> TODO()
                }
            }
        }
    }

    fun createVault() {
        val curState = formState.value
        viewModelScope.launch {
            isSaving.value = true
            curState?.let { state ->
                when (state) {
                    is RecordForm.Login -> {
                        vaultInteractor.createVaultItem(
                            CreateVaultItem.Login(
                                title = state.appNameInput,
                                url = state.urlInput,
                                login = state.loginInput,
                                password = state.passwordInput,
                                categoryId = state.selectedCategoryId
                            )
                        )
                    }
                    is RecordForm.BankCard -> TODO()
                    is RecordForm.SecureNote -> TODO()
                }
            }
            formState.update { current ->
                if (current === curState) null else current
            }
            isSaving.value = false
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val vaultInteractor =
                    (this[APPLICATION_KEY] as KStoreApplication).diContainer.provideVaultInteractor()
                val getCategories =
                    (this[APPLICATION_KEY] as KStoreApplication).diContainer.provideGetCategoriesUseCase()
                VaultFormViewModel(vaultInteractor, getCategories)
            }
        }
    }
}