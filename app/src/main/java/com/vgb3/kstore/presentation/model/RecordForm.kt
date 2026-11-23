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