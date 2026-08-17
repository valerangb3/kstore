package com.vgb3.kstore.domain.model

data class CreateSecureNote(
    val title: String,
    val categoryId: Long?,
    val secret: String
)
