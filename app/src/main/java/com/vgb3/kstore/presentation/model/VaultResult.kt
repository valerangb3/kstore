package com.vgb3.kstore.presentation.model

import com.vgb3.kstore.domain.model.output.VaultItem

sealed interface VaultResult {
    object Loading: VaultResult
    class Error(val message: String): VaultResult
    object Idle: VaultResult
    /*data class VaultFormFields(
        val appNameInput: String,
        val urlInput: String,
        val loginInput: String,
        val passwordInput: String
    ): Result*/
    data class VaultContent(
        val vaultList: List<VaultItem>
    ): VaultResult
}