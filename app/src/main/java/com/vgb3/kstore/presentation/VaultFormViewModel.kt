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
import com.vgb3.kstore.presentation.model.RecordFormUiState
import com.vgb3.kstore.presentation.model.VaultFormInputs
import com.vgb3.kstore.presentation.model.VaultFormUiState
import com.vgb3.kstore.presentation.model.toPreview
import com.vgb3.kstore.presentation.ui.model.RecordFormType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VaultFormViewModel(
    private val vaultInteractor: VaultInteractor,
    getCategories: GetCategoriesUseCase
) : ViewModel() {

    private val formState = MutableStateFlow<RecordForm?>(null)
    private val isSaving = MutableStateFlow(false)

    val uiState = combine(
        formState,
        getCategories(),
        isSaving
    ) { draftFormFields, categories, saving ->
        val form = when (draftFormFields) {
            is RecordForm.Login -> RecordFormUiState.Login(draftFormFields)
            is RecordForm.BankCard -> RecordFormUiState.BankCard(
                draftFields = draftFormFields,
                preview = draftFormFields.toPreview(),
            )
            is RecordForm.SecureNote -> RecordFormUiState.SecureNote(draftFormFields)
            null -> null
        }
        VaultFormUiState(
            form = form,
            categories = categories.map { it.toUi() },
            isSaving = saving,
            canSave = !saving && canSave(form)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = VaultFormUiState()
    )

    private val cardExpiryPattern = Regex("[0-9]{2}/?[0-9]{2}")

    private fun parseCardExpiry(value: String): Pair<Int, Int>? {
        if (!cardExpiryPattern.matches(value)) return null

        val digits = value.replace("/", "")
        val month = digits.take(2).toInt()
        if (month !in 1..12) return null

        return month to digits.drop(2).toInt()
    }

    private fun canSave(form: RecordFormUiState?): Boolean {
        return when (form) {
            is RecordFormUiState.Login -> form.draftFields.appNameInput.isNotBlank() &&
                    form.draftFields.passwordInput.isNotBlank()
            is RecordFormUiState.BankCard -> {
                parseCardExpiry(form.draftFields.cardExpiry)?.let {
                    form.draftFields.cardCvv.isNotBlank() &&
                            form.draftFields.cardNumber.isNotBlank() &&
                            form.draftFields.cardHolder.isNotBlank()
                } ?: false
            }
            is RecordFormUiState.SecureNote -> false
            else -> false
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
                            VaultFormInputs.BankCard.NUMBER -> state.copy(cardNumber =
                                fieldValue
                                    .filter(Char::isDigit)
                                    .take(16)
                            )
                            VaultFormInputs.BankCard.EXPIRY -> state.copy(cardExpiry = fieldValue)
                            VaultFormInputs.BankCard.CVV -> state.copy(cardCvv =
                                fieldValue
                                    .filter(Char::isDigit)
                                    .take(4)
                            )
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
                    is RecordForm.BankCard -> state.copy(selectedCategoryId = categoryId )
                    is RecordForm.SecureNote -> state.copy(selectedCategoryId = categoryId)
                }
            }
        }
    }

    fun createVault(onSaved: () -> Unit) {
        val curState = formState.value
        viewModelScope.launch {
            isSaving.value = true
            try {
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
                        is RecordForm.BankCard -> {
                            val (month, year) = parseCardExpiry(state.cardExpiry) ?: return@launch
                            vaultInteractor.createVaultItem(
                                CreateVaultItem.BankCard(
                                    title = state.cardTitle,
                                    cardNumber = state.cardNumber,
                                    cardholderName = state.cardHolder,
                                    expirationMonth = month,
                                    expirationYear = year,
                                    cvv = state.cardCvv,
                                    categoryId = state.selectedCategoryId
                                )
                            )
                        }
                        is RecordForm.SecureNote -> TODO()
                    }
                }
                val previous = formState.getAndUpdate { current ->
                    if (current === curState) null else current
                }

                if (previous === curState) {
                    onSaved()
                }
            } finally {
                isSaving.value = false
            }

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