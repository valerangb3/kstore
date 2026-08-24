package com.vgb3.kstore.data.model.input

sealed interface VaultData {
    val id: Long
    val title: String
    val createdAt: Long
    val updatedAt: Long
    val isFavorite: Boolean
    val categoryId: Long?

    data class VaultLogin(
        override val id: Long,
        override val categoryId: Long?,
        override val title: String,
        override val updatedAt: Long,
        override val createdAt: Long,
        override val isFavorite: Boolean,
        val url: String,
        val login: String,
        val password: String,
    ): VaultData

    data class VaultBankCard(
        override val id: Long,
        override val categoryId: Long?,
        override val title: String,
        override val updatedAt: Long,
        override val createdAt: Long,
        override val isFavorite: Boolean,

        val cardNumber: String,
        val cardholderName: String,
        val expirationMonth: Int,
        val expirationYear: Int,
        val cvv: String,
    ): VaultData

    data class VaultSecureNote(
        override val id: Long,
        override val categoryId: Long?,
        override val title: String,
        override val updatedAt: Long,
        override val createdAt: Long,
        override val isFavorite: Boolean,

        val note: String,
    ): VaultData
}