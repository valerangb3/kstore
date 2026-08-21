package com.vgb3.kstore.data.model.output

import com.vgb3.kstore.data.model.VaultItemType

data class VaultSummaryRowData(
    val id: Long,
    val title: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isFavorite: Boolean,
    val categoryId: Int?,
    val type: VaultItemType,

    val login: String?,
    val cardLastFour: String?,
)
