package com.vgb3.kstore.domain.model


sealed interface VaultItem {
    val id: Long
    val title: String
    val createdAt: Long
    val updatedAt: Long
    val isFavorite: Boolean
    val icon: String?
    val categoryId: Long?

    data class VaultLogin(
        override val id: Long,
        override val categoryId: Long?,
        override val title: String,
        override val updatedAt: Long,
        override val createdAt: Long,
        override val isFavorite: Boolean,
        override val icon: String?,

        val url: String,
        val login: String,
        val password: String,
    ): VaultItem
}


data class VaultItem1(
    val id: String = "0",
    val appName: String,
    val url: String,
    val login: String,
    val password: String,
)