package com.vgb3.kstore.data.repository

import com.vgb3.kstore.data.datasource.local.db.dao.VaultDao
import com.vgb3.kstore.data.datasource.local.db.entities.toVaultLoginData
import com.vgb3.kstore.data.model.toVaultEntity
import com.vgb3.kstore.data.model.toVaultItem
import com.vgb3.kstore.data.model.toVaultLoginData
import com.vgb3.kstore.domain.model.output.VaultItem
import com.vgb3.kstore.domain.repository.VaultRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class VaultRepositoryImpl(
    private val vaultDao: VaultDao,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): VaultRepository {

    private suspend fun loadIcon() {
        //TODO need implement load icon
    }

    override suspend fun createVaultItem(vaultItem: VaultItem): Long {
        return when(vaultItem) {
            is VaultItem.VaultLogin -> vaultDao.insertNewVault(
                vaultItem
                    .toVaultLoginData()
                    .toVaultEntity()
            )
            is VaultItem.VaultBankCard -> TODO()
            is VaultItem.VaultSecureNote -> TODO()
        }
    }

    override fun getVaultItems(): Flow<List<VaultItem>> {
        return vaultDao.getVaultItems()
            .map { vaultEntity ->
                vaultEntity.map { it
                    .toVaultLoginData()
                    .toVaultItem()
                }
            }
            .flowOn(coroutineDispatcher)
    }
}