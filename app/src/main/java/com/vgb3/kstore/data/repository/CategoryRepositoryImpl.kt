package com.vgb3.kstore.data.repository

import com.vgb3.kstore.data.datasource.local.db.dao.VaultDao
import com.vgb3.kstore.data.utils.map.toCategory
import com.vgb3.kstore.domain.model.output.Category
import com.vgb3.kstore.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val dao: VaultDao
) : CategoryRepository {
    override fun observeCategory(): Flow<List<Category>> {
        return dao
            .getCategories()
            .map { categories ->
                categories.map {
                    it.toCategory()
                }
            }
    }
}