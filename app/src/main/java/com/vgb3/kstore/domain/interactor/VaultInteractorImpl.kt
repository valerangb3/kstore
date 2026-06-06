package com.vgb3.kstore.domain.interactor

import com.vgb3.kstore.domain.model.VaultItem
import com.vgb3.kstore.domain.repository.VaultRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class VaultInteractorImpl(
    private val vaultRepository: VaultRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): VaultInteractor {
    override suspend fun createVaultItem(vaultItem: VaultItem): Long {
        var rowId = -1L
        withContext(coroutineDispatcher) {
            rowId = vaultRepository.createVaultItem(vaultItem)
        }
        return rowId
    }

    override fun getVaultItems(): Flow<List<VaultItem>> {
        return vaultRepository
            .getVaultItems()
            .flowOn(coroutineDispatcher)
    }
}