package com.vgb3.kstore.domain.repository

import com.vgb3.kstore.domain.model.output.VaultItem
import com.vgb3.kstore.domain.model.output.VaultItemSummary
import kotlinx.coroutines.flow.Flow

interface VaultRepository {
    fun getVaultSummaryItems(): Flow<List<VaultItemSummary>>
    suspend fun createVaultItem(vaultItem: VaultItem): Long
}