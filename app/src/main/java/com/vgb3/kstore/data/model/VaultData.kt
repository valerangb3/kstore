package com.vgb3.kstore.data.model

import com.vgb3.kstore.data.datasource.local.db.entities.VaultEntity
import com.vgb3.kstore.domain.model.VaultItem

data class VaultData(
    val id: Long = 0,
    val title: String,
    val url: String,
    val login: String,
    val password: String,
    val categoryId: Int?,
    val updatedAt: Long,
    val createdAt: Long,
    val icon: String?,
    val isFavorite: Boolean
)

fun VaultData.toVaultItem(): VaultItem = VaultItem.VaultLogin(
    id = this.id,
    title = this.title,
    url = this.url,
    login = this.login,
    password = this.password,
    categoryId = null,
    updatedAt = 1L,
    createdAt = 1L,
    icon = null,
    isFavorite = false
)


fun VaultData.toVaultEntity(): VaultEntity = VaultEntity(
    id = this.id,
    title = this.title,
    icon = this.icon ?: "",
    login = this.login,
    password = this.password
)