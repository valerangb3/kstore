package com.vgb3.kstore.data.utils.map

import com.vgb3.kstore.data.model.VaultItemType
import com.vgb3.kstore.data.model.output.VaultSummaryRowData
import com.vgb3.kstore.domain.model.output.VaultItemSummary
import kotlin.String

private fun VaultItemType.getSubTitle(row: VaultSummaryRowData): String? = when (this) {
    VaultItemType.LOGIN -> row.login
    VaultItemType.BANK_CARD -> row.cardLastFour?.let { "**** $it" }
    VaultItemType.SECURE_NOTE -> null
}

fun VaultSummaryRowData.toVaultItemSummary() = VaultItemSummary(
    id = this.id,
    title = this.title,
    subTitle = this.type.getSubTitle(this),
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    isFavorite = this.isFavorite,
    categoryId = this.categoryId,
)