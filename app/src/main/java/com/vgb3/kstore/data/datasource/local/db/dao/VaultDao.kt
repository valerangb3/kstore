package com.vgb3.kstore.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vgb3.kstore.data.datasource.local.db.entities.VaultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VaultDao {
    @Insert(entity = VaultEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewVault(vault: VaultEntity): Long
    @Query("SELECT * FROM vault_table")
    fun getVaultItems(): Flow<List<VaultEntity>>
}