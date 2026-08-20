package com.vgb3.kstore.data.model

import com.vgb3.kstore.data.datasource.local.db.entities.VaultEntity
import com.vgb3.kstore.domain.model.output.VaultItem

sealed interface VaultData {
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
    ): VaultData

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
    ): VaultData

    data class VaultSecureNote(
        override val id: Long,
        override val categoryId: Int?,
        override val title: String,
        override val updatedAt: Long,
        override val createdAt: Long,
        override val isFavorite: Boolean,

        val note: String,
    ): VaultData
}

fun VaultItem.VaultLogin.toVaultLoginData(): VaultData.VaultLogin = VaultData.VaultLogin(
    id = this.id,
    title = this.title,
    url = this.url,
    login = this.login,
    password = this.password,
    categoryId = this.categoryId,
    updatedAt = this.updatedAt,
    createdAt = this.createdAt,
    isFavorite = false
)


fun VaultData.VaultLogin.toVaultItem(): VaultItem = VaultItem.VaultLogin(
    id = this.id,
    title = this.title,
    url = this.url,
    login = this.login,
    password = this.password,
    categoryId = this.categoryId,
    updatedAt = this.updatedAt,
    createdAt = this.createdAt,
    isFavorite = this.isFavorite
)


fun VaultData.VaultLogin.toVaultEntity(): VaultEntity = VaultEntity(
    id = this.id,
    title = this.title,
    login = this.login,
    password = this.password,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    isFavorite = this.isFavorite
)