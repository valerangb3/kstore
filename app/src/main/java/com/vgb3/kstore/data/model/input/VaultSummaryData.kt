package com.vgb3.kstore.data.model.input

import com.vgb3.kstore.data.model.VaultItemType

data class VaultSummaryData(
    val id: Long,
    val title: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isFavorite: Boolean,
    val categoryId: Long?,
    val type: VaultItemType,
)