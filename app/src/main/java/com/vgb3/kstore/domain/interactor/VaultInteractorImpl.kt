package com.vgb3.kstore.domain.interactor

import com.vgb3.kstore.domain.model.VaultItem
import kotlinx.coroutines.flow.Flow

class VaultInteractorImpl: VaultInteractor {
    override suspend fun createVaultItem(vaultItem: VaultItem): VaultItem? {
        TODO("Not yet implemented")
    }

    override fun getVaultItems(): Flow<List<VaultItem>> {
        TODO("Not yet implemented")
    }
}