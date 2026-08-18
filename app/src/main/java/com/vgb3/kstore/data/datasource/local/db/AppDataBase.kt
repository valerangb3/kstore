package com.vgb3.kstore.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vgb3.kstore.data.datasource.local.db.dao.VaultDao
import com.vgb3.kstore.data.datasource.local.db.entities.VaultEntity


@Database(
    version = 2,
    entities = [
        VaultEntity::class
    ]
)
abstract class KStoreDataBase : RoomDatabase() {
    abstract fun getVaultDao(): VaultDao
}