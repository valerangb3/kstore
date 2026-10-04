package com.vgb3.kstore.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vgb3.kstore.data.datasource.local.db.KStoreDataBase
import com.vgb3.kstore.data.datasource.local.db.entities.VaultCategoryEntity
import com.vgb3.kstore.data.datasource.local.db.entities.VaultRecordTypeEntity
import com.vgb3.kstore.data.datasource.local.db.migrations.MIGRATION_1_2
import com.vgb3.kstore.data.repository.CategoryRepositoryImpl
import com.vgb3.kstore.data.repository.RecordTypeRepositoryImpl
import com.vgb3.kstore.data.repository.VaultRepositoryImpl
import com.vgb3.kstore.domain.interactor.VaultInteractor
import com.vgb3.kstore.domain.interactor.VaultInteractorImpl
import com.vgb3.kstore.domain.repository.CategoryRepository
import com.vgb3.kstore.domain.repository.RecordTypeRepository
import com.vgb3.kstore.domain.repository.VaultRepository
import com.vgb3.kstore.domain.usecase.GetCategoriesUseCase
import com.vgb3.kstore.domain.usecase.GetRecordTypesUseCase
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

    fun provideGetRecordTypesUseCase(): GetRecordTypesUseCase {
        return GetRecordTypesUseCase(recordTypeRepository = provideRecordTypeRepository())
    }

    fun provideGetCategoriesUseCase(): GetCategoriesUseCase {
        return GetCategoriesUseCase(provideCategoryRepository())
    }

    private fun provideRecordTypeRepository(): RecordTypeRepository {
        return RecordTypeRepositoryImpl(dao = provideVaultDao())
    }

    private fun provideCategoryRepository(): CategoryRepository {
        return CategoryRepositoryImpl(dao = provideVaultDao())
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
                .addMigrations(MIGRATION_1_2)
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
                                dao.insertRecordType(
                                    listOf(
                                        VaultRecordTypeEntity(
                                            code = "login",
                                            sortOrder = 5,
                                        ),
                                        VaultRecordTypeEntity(
                                            code = "bank_card",
                                            sortOrder = 10,
                                        ),
                                        VaultRecordTypeEntity(
                                            code = "secure_note",
                                            sortOrder = 15,
                                        )
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