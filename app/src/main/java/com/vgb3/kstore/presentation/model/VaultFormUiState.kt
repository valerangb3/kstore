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
    val input: VaultFormInput = VaultFormInput(),
    val categories: List<DropdownOption> = emptyList(),
    val isSaving: Boolean = false,
    val canSave: Boolean = false,
)

sealed interface VaultFormResult1 {
    object Idle: VaultFormResult1
    data class VaultFormFields(
        val appNameInput: String = "",
        val urlInput: String = "",
        val loginInput: String = "",
        val passwordInput: String = "",
        val selectedCategoryId: Long? = null
    ): VaultFormResult1
}