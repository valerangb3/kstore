package com.vgb3.kstore.data.datasource.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vgb3.kstore.data.model.VaultData

/*
*
    val id: Long
    val title: String
    val createdAt: Long
    val updatedAt: Long
    val isFavorite: Boolean
    val categoryId: Int?
*
* */


@Entity(tableName = "vault_table")
data class VaultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isFavorite: Boolean,
    val categoryId: Int
)

@Entity(tableName = "vault_login_table")
data class VaultLoginEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val login: String,
    val password: String,
    val urlString: String,
)

fun VaultEntity.toVaultLoginData(): VaultData.VaultLogin = VaultData.VaultLogin(
    id = this.id,
    title = this.title,
    url = "",
    login = this.login,
    password = this.password,
    categoryId = null,
    updatedAt = 1,
    createdAt = 1,
    isFavorite = false
)