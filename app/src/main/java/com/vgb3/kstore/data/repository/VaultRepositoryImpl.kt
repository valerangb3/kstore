package com.vgb3.kstore.data.repository

import com.vgb3.kstore.data.datasource.local.db.dao.VaultDao
import com.vgb3.kstore.data.datasource.local.db.entities.toVaultData
import com.vgb3.kstore.data.model.VaultData
import com.vgb3.kstore.data.model.toVaultEntity
import com.vgb3.kstore.data.model.toVaultItem
import com.vgb3.kstore.domain.model.VaultItem
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
    override suspend fun createVaultItem(vaultItem: VaultItem): Long {
        val vaultData = VaultData(
            id = "0",
            appName = vaultItem.appName,
            url = vaultItem.url,
            login = vaultItem.login,
            password = vaultItem.password
        )
        return vaultDao.insertNewVault(vaultData.toVaultEntity())
    }

    override fun getVaultItems(): Flow<List<VaultItem>> {
        return vaultDao.getVaultItems()
            .map { vaultEntity ->
                vaultEntity.map { it
                    .toVaultData()
                    .toVaultItem()
                }
            }
            .flowOn(coroutineDispatcher)
    }
}