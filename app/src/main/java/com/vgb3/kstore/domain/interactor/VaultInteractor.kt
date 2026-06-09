package com.vgb3.kstore.domain.interactor

import com.vgb3.kstore.domain.model.VaultItem
import kotlinx.coroutines.flow.Flow

interface VaultInteractor {
    fun getVaultItems(): Flow<List<VaultItem>>
    suspend fun createVaultItem(vaultItem: VaultItem): Long
}