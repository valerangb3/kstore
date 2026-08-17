package com.vgb3.kstore.domain.model

data class CreateLogin(
    val appName: String,
    val url: String,
    val categoryId: Long?,
    val login: String,
    val password: String,
)
