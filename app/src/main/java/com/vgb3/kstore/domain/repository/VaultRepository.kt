package com.vgb3.kstore.domain.repository

import com.vgb3.kstore.domain.model.VaultItem
import kotlinx.coroutines.flow.Flow

interface VaultRepository {
    fun getVaultItems(): Flow<List<VaultItem>>
    suspend fun createVaultItem(vaultItem: VaultItem): Long
}