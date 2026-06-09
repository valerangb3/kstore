package com.vgb3.kstore.domain.model

data class VaultItem(
    val id: String = "0",
    val appName: String,
    val url: String,
    val login: String,
    val password: String,
)
