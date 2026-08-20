package com.vgb3.kstore.di

import android.content.Context
import androidx.room.Room
import com.vgb3.kstore.data.datasource.local.db.KStoreDataBase
import com.vgb3.kstore.data.datasource.local.db.dao.VaultDao
import com.vgb3.kstore.data.datasource.local.db.migrations.MIGRATION_1_2
import com.vgb3.kstore.data.repository.VaultRepositoryImpl
import com.vgb3.kstore.domain.interactor.VaultInteractor
import com.vgb3.kstore.domain.interactor.VaultInteractorImpl
import com.vgb3.kstore.domain.repository.VaultRepository

class Container(
    private val context: Context
) {
    fun provideVaultInteractor(): VaultInteractor {
        return VaultInteractorImpl(
            vaultRepository = provideVaultRepository()
        )
    }

    private fun provideVaultRepository(): VaultRepository {
        return VaultRepositoryImpl(
            provideVaultDao()
        )
    }

    private fun provideVaultDao(): VaultDao {
        val dataBase = Room.databaseBuilder(
            context,
            KStoreDataBase::class.java,
            "database.db"
        )
            .build()
        return dataBase.getVaultDao()
    }
}