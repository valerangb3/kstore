package com.vgb3.kstore.data.model

import com.vgb3.kstore.data.datasource.local.db.entities.VaultEntity
import com.vgb3.kstore.domain.model.VaultItem

data class VaultData(
    val id: String,
    val appName: String,
    val url: String,
    val login: String,
    val password: String,
)

fun VaultData.toVaultItem(): VaultItem = VaultItem(
    id = this.id,
    appName = this.appName,
    url = this.url,
    login = this.login,
    password = this.password
)


fun VaultData.toVaultEntity(): VaultEntity = VaultEntity(
    id = this.id.toLong(),
    appName = this.appName,
    picUrl = this.url,
    login = this.login,
    password = this.password
)