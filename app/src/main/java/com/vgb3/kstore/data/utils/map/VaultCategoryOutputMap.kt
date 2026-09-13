package com.vgb3.kstore.data.utils.map

import com.vgb3.kstore.data.datasource.local.db.entities.VaultCategoryEntity
import com.vgb3.kstore.domain.model.output.Category

fun VaultCategoryEntity.toCategory(): Category = Category(
    id = this.id,
    key = this.key
)