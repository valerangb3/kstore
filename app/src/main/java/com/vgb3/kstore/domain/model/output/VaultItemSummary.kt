package com.vgb3.kstore.domain.model.output

data class VaultItemSummary(
    val id: Long,
    val title: String,
    val subTitle: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val isFavorite: Boolean,
    val categoryId: Int?,
)
