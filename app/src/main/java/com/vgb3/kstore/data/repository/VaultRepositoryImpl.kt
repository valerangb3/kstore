package com.vgb3.kstore.data.repository

import com.vgb3.kstore.data.datasource.local.db.dao.VaultDao
import com.vgb3.kstore.data.model.VaultItemType
import com.vgb3.kstore.data.utils.map.toVaultEntity
import com.vgb3.kstore.data.utils.map.toVaultItemSummary
import com.vgb3.kstore.data.utils.map.toVaultLoginData
import com.vgb3.kstore.data.utils.map.toVaultLoginEntity
import com.vgb3.kstore.data.utils.map.toVaultSummaryData
import com.vgb3.kstore.domain.model.output.VaultItem
import com.vgb3.kstore.domain.model.output.VaultItemSummary
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

    override fun getVaultSummaryItems(): Flow<List<VaultItemSummary>> {
        return vaultDao.getVaultSummaryItems()
            .map { vaultSummaryRow ->
                vaultSummaryRow.map { row -> row.toVaultItemSummary()}
            }
            .flowOn(coroutineDispatcher)
    }

    override suspend fun createVaultItem(vaultItem: VaultItem): Long {
        return when(vaultItem) {
            is VaultItem.VaultLogin -> vaultDao.insertVaultLoginItem(
                item = vaultItem
                    .toVaultSummaryData()
                    .toVaultEntity(VaultItemType.LOGIN),
                login = vaultItem
                    .toVaultLoginData()
                    .toVaultLoginEntity()
            )
            is VaultItem.VaultBankCard -> TODO()
            is VaultItem.VaultSecureNote -> TODO()
        }
    }
}