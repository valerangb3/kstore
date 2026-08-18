package com.vgb3.kstore.data.repository

import com.vgb3.kstore.data.datasource.local.db.dao.VaultDao
import com.vgb3.kstore.data.datasource.local.db.entities.toVaultData
import com.vgb3.kstore.data.model.VaultData
import com.vgb3.kstore.data.model.toVaultEntity
import com.vgb3.kstore.data.model.toVaultItem
import com.vgb3.kstore.domain.model.CreateLogin
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

    private suspend fun loadIcon() {
        //TODO need implement load icon
    }

    override suspend fun createVaultItem(vaultCreateLogin: CreateLogin): Long {
        val timestamp = System.currentTimeMillis()
        val vaultData = VaultData(
            title = vaultCreateLogin.appName,
            url = vaultCreateLogin.url,
            login = vaultCreateLogin.login,
            password = vaultCreateLogin.password,
            categoryId = null,
            icon = null,
            updatedAt = timestamp,
            createdAt = timestamp,
            isFavorite = false
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