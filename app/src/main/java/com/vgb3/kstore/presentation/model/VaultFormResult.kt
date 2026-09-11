package com.vgb3.kstore.presentation.model


sealed interface VaultFormResult {
    object Idle: VaultFormResult
    data class VaultFormFields(
        val appNameInput: String = "",
        val urlInput: String = "",
        val loginInput: String = "",
        val passwordInput: String = "",
        val categoryId: Int = 0
    ): VaultFormResult
}