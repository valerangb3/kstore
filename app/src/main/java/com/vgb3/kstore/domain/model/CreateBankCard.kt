package com.vgb3.kstore.domain.model

data class CreateBankCard(
    val categoryId: Long?,
    val cardNumber: String,
    val cvv: Int
)
