package com.vgb3.kstore.domain.interactor

import com.vgb3.kstore.domain.model.input.CreateVaultItem
import com.vgb3.kstore.domain.model.output.VaultItem
import com.vgb3.kstore.domain.model.output.VaultItemSummary
import kotlinx.coroutines.flow.Flow

interface VaultInteractor {
    fun getVaultSummaryItems(): Flow<List<VaultItemSummary>>
    suspend fun createVaultItem(createVaultItem: CreateVaultItem): Long
}