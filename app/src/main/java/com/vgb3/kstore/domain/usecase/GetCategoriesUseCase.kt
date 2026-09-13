package com.vgb3.kstore.domain.usecase

import com.vgb3.kstore.domain.model.output.Category
import com.vgb3.kstore.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<List<Category>> = categoryRepository.observeCategory()
}