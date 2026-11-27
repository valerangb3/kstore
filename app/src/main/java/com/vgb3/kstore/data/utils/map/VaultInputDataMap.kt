package com.vgb3.kstore.data.utils.map

import com.vgb3.kstore.data.datasource.local.db.entities.VaultBankCardEntity
import com.vgb3.kstore.data.datasource.local.db.entities.VaultEntity
import com.vgb3.kstore.data.datasource.local.db.entities.VaultLoginEntity
import com.vgb3.kstore.data.model.input.VaultData
import com.vgb3.kstore.data.model.VaultItemType
import com.vgb3.kstore.data.model.input.VaultSummaryData
import com.vgb3.kstore.domain.model.output.VaultItem

fun VaultSummaryData.toVaultEntity(): VaultEntity = VaultEntity(
    id = this.id,
    title = this.title,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    isFavorite = this.isFavorite,
    categoryId = this.categoryId,
    type = this.type
)

fun VaultItem.toVaultSummaryData(type: VaultItemType): VaultSummaryData = VaultSummaryData(
    id = this.id,
    title = this.title,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    isFavorite = this.isFavorite,
    categoryId = this.categoryId,
    type = type,
)

fun VaultItem.VaultLogin.toVaultLoginData(): VaultData.VaultLogin = VaultData.VaultLogin(
    id = this.id,
    title = this.title,
    url = this.url,
    login = this.login,
    password = this.password,
    categoryId = this.categoryId,
    updatedAt = this.updatedAt,
    createdAt = this.createdAt,
    isFavorite = this.isFavorite
)

fun VaultData.VaultLogin.toVaultLoginEntity(): VaultLoginEntity = VaultLoginEntity(
    vaultItemId = this.id,
    login = this.login,
    password = this.password,
    urlString = this.url,
)

fun VaultItem.VaultBankCard.toVaultBankCardData(): VaultData.VaultBankCard = VaultData.VaultBankCard(
    id = this.id,
    categoryId = this.categoryId,
    title = this.title,
    updatedAt = this.updatedAt,
    createdAt = this.createdAt,
    isFavorite = this.isFavorite,
    cardNumber = this.cardNumber,
    cardholderName = this.cardholderName,
    expirationMonth = this.expirationMonth,
    expirationYear = this.expirationYear,
    cvv = this.cvv
)

fun VaultData.VaultBankCard.toVaultBankCardEntity(): VaultBankCardEntity = VaultBankCardEntity(
    vaultItemId = this.id,
    cardNumber = this.cardNumber,
    cardholderName = this.cardholderName,
    expirationMonth = this.expirationMonth,
    expirationYear = this.expirationYear,
    cvv = this.cvv
)