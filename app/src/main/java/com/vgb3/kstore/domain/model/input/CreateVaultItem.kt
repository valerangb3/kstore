package com.vgb3.kstore.domain.model.input

sealed interface CreateVaultItem {
    data class Login(
        val title: String,
        val url: String,
        val categoryId: Long?,
        val login: String,
        val password: String,
    ) : CreateVaultItem

    data class BankCard(
        val title: String,
        val cardNumber: String,
        val cardholderName: String,
        val expirationMonth: Int,
        val expirationYear: Int,
        val cvv: String,
        val categoryId: Long?,
    ) : CreateVaultItem

    data class SecureNote(
        val title: String,
        val categoryId: Long?,
        val secret: String
    ) : CreateVaultItem
}