package com.vgb3.kstore.domain.interactor

import com.vgb3.kstore.domain.model.input.CreateVaultItem
import com.vgb3.kstore.domain.model.output.VaultItem
import kotlinx.coroutines.flow.Flow

interface VaultInteractor {
    fun getVaultItems(): Flow<List<VaultItem>>
    suspend fun createVaultItem(createVaultItem: CreateVaultItem): Long
}