package com.vgb3.kstore.data.datasource.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vgb3.kstore.data.model.VaultData

@Entity(tableName = "vault_table")
data class VaultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val appName: String,
    val picUrl: String,
    val login: String,
    val password: String,
)

fun VaultEntity.toVaultData(): VaultData = VaultData(
    id = this.id.toString(),
    appName = this.appName,
    url = this.picUrl,
    login = this.login,
    password = this.password
)