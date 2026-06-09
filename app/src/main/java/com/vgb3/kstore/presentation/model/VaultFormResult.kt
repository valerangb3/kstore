package com.vgb3.kstore.presentation.model

import com.vgb3.kstore.domain.model.VaultItem


sealed interface VaultFormResult {
    object Idle: VaultFormResult
    data class VaultFormFields(
        val appNameInput: String = "",
        val urlInput: String = "",
        val loginInput: String = "",
        val passwordInput: String = ""
    ): VaultFormResult
}