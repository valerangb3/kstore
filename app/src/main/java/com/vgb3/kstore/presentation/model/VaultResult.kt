package com.vgb3.kstore.presentation.model

import com.vgb3.kstore.domain.model.output.VaultItemSummary
import com.vgb3.kstore.presentation.ui.model.RecordTypeUi

sealed interface VaultResult {
    object Loading: VaultResult
    class Error(val message: String): VaultResult
    object Idle: VaultResult
    data class VaultContent(
        val vaultList: List<VaultItemSummary>,
    ): VaultResult
}