package com.vgb3.kstore.data.datasource.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vgb3.kstore.data.model.VaultData

@Entity(tableName = "vault_table")
data class VaultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val icon: String,
    val login: String,
    val password: String,
)

fun VaultEntity.toVaultData(): VaultData = VaultData(
    id = this.id,
    title = this.title,
    url = "",
    icon = this.icon,
    login = this.login,
    password = this.password,
    categoryId = null,
    updatedAt = 1,
    createdAt = 1,
    isFavorite = false
)