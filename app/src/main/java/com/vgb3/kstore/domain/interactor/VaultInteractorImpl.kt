package com.vgb3.kstore.domain.interactor

import com.vgb3.kstore.domain.model.input.CreateVaultItem
import com.vgb3.kstore.domain.model.output.VaultItem
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
    override suspend fun createVaultItem(createVaultItem: CreateVaultItem): Long {
        val now = System.currentTimeMillis()
        val item = when(createVaultItem) {
            is CreateVaultItem.Login -> VaultItem.VaultLogin(
                id = 0,
                title = createVaultItem.title,
                createdAt = now,
                updatedAt = now,
                isFavorite = false,
                categoryId = createVaultItem.categoryId,
                login = createVaultItem.login,
                password = createVaultItem.password,
                url = createVaultItem.url
            )
            is CreateVaultItem.BankCard -> TODO()
            is CreateVaultItem.SecureNote -> TODO()
        }
        return withContext(coroutineDispatcher) {
            vaultRepository.createVaultItem(item)
        }
    }

    override fun getVaultItems(): Flow<List<VaultItem>> {
        return vaultRepository
            .getVaultItems()
            .flowOn(coroutineDispatcher)
    }
}