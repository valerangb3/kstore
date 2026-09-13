package com.vgb3.kstore.domain.repository

import com.vgb3.kstore.domain.model.output.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun observeCategory(): Flow<List<Category>>
}