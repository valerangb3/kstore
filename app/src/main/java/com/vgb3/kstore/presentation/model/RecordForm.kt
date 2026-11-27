package com.vgb3.kstore.presentation.model

sealed interface RecordForm {
    val selectedCategoryId: Long?

    data class Login(
        val appNameInput: String = "",
        val urlInput: String = "",
        val loginInput: String = "",
        val passwordInput: String = "",
       override val selectedCategoryId: Long? = null
    ) : RecordForm

    data class BankCard(
        val cardTitle: String = "",
        val cardNumber: String = "",
        val cardExpiry: String = "",
        val cardCvv: String = "",
        val cardHolder: String = "",
        override val selectedCategoryId: Long? = null
    ) : RecordForm

    data class SecureNote(
        val secureTitle: String = "",
        val secureText: String = "",
        override val selectedCategoryId: Long? = null
    ) : RecordForm
}

fun RecordForm.BankCard.toPreview(): BankCardPreviewUiState {
    val expiryDigits = cardExpiry.filter(Char::isDigit).take(4)
    val month = expiryDigits.take(2)
    val year = expiryDigits.drop(2)
    return BankCardPreviewUiState(
        lastFour = cardNumber.filter(Char::isDigit).drop(12).take(4),
        holderName = cardHolder,
        expiry = if (year.isEmpty()) month else "$month/$year",
    )
}

data class BankCardPreviewUiState(
    val lastFour: String = "••••",
    val holderName: String = "",
    val expiry: String = "",
)

sealed interface RecordFormUiState {
    data class Login(
        val draftFields: RecordForm.Login
    ) : RecordFormUiState

    data class BankCard(
        val draftFields: RecordForm.BankCard,
        val preview: BankCardPreviewUiState
    ) : RecordFormUiState

    data class SecureNote(
        val draftFields: RecordForm.SecureNote
    ) : RecordFormUiState
}