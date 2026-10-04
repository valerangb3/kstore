package com.vgb3.kstore.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vgb3.kstore.data.datasource.local.db.dao.VaultDao
import com.vgb3.kstore.data.datasource.local.db.entities.VaultBankCardEntity
import com.vgb3.kstore.data.datasource.local.db.entities.VaultCategoryEntity
import com.vgb3.kstore.data.datasource.local.db.entities.VaultEntity
import com.vgb3.kstore.data.datasource.local.db.entities.VaultLoginEntity
import com.vgb3.kstore.data.datasource.local.db.entities.VaultRecordTypeEntity
import com.vgb3.kstore.data.datasource.local.db.entities.VaultSecureNoteEntity


@Database(
    version = 2,
    entities = [
        VaultEntity::class,
        VaultLoginEntity::class,
        VaultBankCardEntity::class,
        VaultSecureNoteEntity::class,
        VaultCategoryEntity::class,
        VaultRecordTypeEntity::class
    ]
)
abstract class KStoreDataBase : RoomDatabase() {
    abstract fun getVaultDao(): VaultDao
}