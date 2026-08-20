package com.vgb3.kstore.domain.model.output

sealed interface VaultItem {
    val id: Long
    val title: String
    val createdAt: Long
    val updatedAt: Long
    val isFavorite: Boolean
    val categoryId: Int?

    data class VaultLogin(
        override val id: Long,
        override val categoryId: Int?,
        override val title: String,
        override val updatedAt: Long,
        override val createdAt: Long,
        override val isFavorite: Boolean,
        val url: String,
        val login: String,
        val password: String,
    ): VaultItem

    data class VaultBankCard(
        override val id: Long,
        override val categoryId: Int?,
        override val title: String,
        override val updatedAt: Long,
        override val createdAt: Long,
        override val isFavorite: Boolean,

        val cardNumber: String,
        val cardholderName: String,
        val expirationMonth: Int,
        val expirationYear: Int,
        val cvv: String,
    ): VaultItem

    data class VaultSecureNote(
        override val id: Long,
        override val categoryId: Int?,
        override val title: String,
        override val updatedAt: Long,
        override val createdAt: Long,
        override val isFavorite: Boolean,

        val note: String,
    ): VaultItem
}