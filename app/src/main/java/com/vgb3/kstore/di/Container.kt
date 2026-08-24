package com.vgb3.kstore.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vgb3.kstore.data.datasource.local.db.KStoreDataBase
import com.vgb3.kstore.data.datasource.local.db.entities.VaultCategoryEntity
import com.vgb3.kstore.data.repository.VaultRepositoryImpl
import com.vgb3.kstore.domain.interactor.VaultInteractor
import com.vgb3.kstore.domain.interactor.VaultInteractorImpl
import com.vgb3.kstore.domain.repository.VaultRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.concurrent.Volatile

class Container(
    private val context: Context
) {

    val appScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    @Volatile
    private var instance: KStoreDataBase? = null

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

    private fun provideVaultDao() = getDataBase().getVaultDao()

    private fun getDataBase(): KStoreDataBase {
        return instance ?: synchronized(this) {
            val db = Room.databaseBuilder(
                context,
                KStoreDataBase::class.java,
                "database.db"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        instance?.let { database ->
                            appScope.launch {
                                val dao = database.getVaultDao()
                                dao.insertCategoryVault(
                                    listOf(
                                        VaultCategoryEntity(key = "PERSONAL", sortOrder = 5),
                                        VaultCategoryEntity(key = "WORK", sortOrder = 10),
                                        VaultCategoryEntity(key = "FINANCE", sortOrder = 15),
                                        VaultCategoryEntity(key = "SOCIAL", sortOrder = 20),
                                        VaultCategoryEntity(key = "SHOPPING", sortOrder = 25),
                                        VaultCategoryEntity(key = "EDUCATION", sortOrder = 30),
                                    )
                                )
                            }
                        }
                    }
                })
                .build()
            instance = db
            db
        }
    }
}