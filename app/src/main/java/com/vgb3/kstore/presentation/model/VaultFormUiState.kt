package com.vgb3.kstore.presentation.model

import com.vgb3.kstore.presentation.ui.model.DropdownOption

data class VaultFormInput(
    val appNameInput: String = "",
    val urlInput: String = "",
    val loginInput: String = "",
    val passwordInput: String = "",
    val selectedCategoryId: Long? = null
)

data class VaultFormUiState(
    val form: RecordFormUiState? = null,
    val categories: List<DropdownOption> = emptyList(),
    val isSaving: Boolean = false,
    val canSave: Boolean = false,
)