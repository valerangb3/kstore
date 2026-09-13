package com.vgb3.kstore.presentation.map

import androidx.annotation.StringRes
import com.vgb3.kstore.R
import com.vgb3.kstore.domain.model.output.Category
import com.vgb3.kstore.presentation.ui.model.DropdownOption

fun Category.toUi(): DropdownOption = DropdownOption(
    id = this.id,
    key = this.key,
    titleRes = this.key.toTitleRes()
)

@StringRes
private fun String.toTitleRes() = when (this) {
    "PERSONAL" -> R.string.category_personal
    "WORK" -> R.string.category_work
    "FINANCE" -> R.string.category_finance
    "SOCIAL" -> R.string.category_social
    "SHOPPING" -> R.string.category_shopping
    "EDUCATION" -> R.string.category_education
    else -> R.string.category_unknown
}