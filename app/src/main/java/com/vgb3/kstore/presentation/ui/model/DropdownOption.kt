package com.vgb3.kstore.presentation.ui.model

import androidx.annotation.StringRes

data class DropdownOption(
    val id: Long,
    val key: String,
    @StringRes val titleRes: Int
)