package com.vgb3.kstore.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.vgb3.kstore.data.datasource.local.db.entities.VaultEntity
import com.vgb3.kstore.data.datasource.local.db.entities.VaultLoginEntity
import com.vgb3.kstore.data.model.input.VaultSummaryData
import com.vgb3.kstore.data.model.output.VaultSummaryRowData
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultDao {
    @Insert(entity = VaultEntity::class)
    suspend fun insertNewVault(vault: VaultEntity): Long

    @Insert(entity = VaultLoginEntity::class)
    suspend fun insertLoginVault(vaultLogin: VaultLoginEntity): Long

    @Query("SELECT * FROM vault_table")
    fun getVaultSummaryItemsOld(): Flow<List<VaultEntity>>

    @Query("""
        SELECT
            vault.id,
            vault.title,
            vault.createdAt,
            vault.updatedAt,
            vault.isFavorite,
            vault.categoryId,
            vault.type,
            
            login.login AS login,
            substr(card.cardNumber, -4) AS cardLastFour
            
        FROM vault_table AS vault
        
        LEFT JOIN vault_login_table as login
            ON login.vaultItemId = vault.id
            
        LEFT JOIN vault_bank_card_table as card
            ON card.vaultItemId = vault.id
    """)
    fun getVaultSummaryItems(): Flow<List<VaultSummaryRowData>>

    @Query("SELECT * FROM vault_login_table WHERE vaultItemId = :vaultLoginId")
    suspend fun getVaultLoginItem(vaultLoginId: Long): VaultLoginEntity

    @Transaction
    suspend fun insertVaultLoginItem(item: VaultEntity, login: VaultLoginEntity): Long {
        val generatedId = insertNewVault(item)
        insertLoginVault(login.copy(vaultItemId = generatedId))
        return generatedId
    }

}