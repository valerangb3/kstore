package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.ui.model.RecordFormType
import com.vgb3.kstore.presentation.ui.widgets.RecordFormTopBar
import com.vgb3.kstore.presentation.ui.widgets.bottomBorder
import com.vgb3.kstore.ui.theme.KStoreTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CreateRecordScreen(
    type: RecordFormType,
    vaultFormViewModel: VaultFormViewModel,
    onClose: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    val titleRes = when (type) {
        RecordFormType.LOGIN -> R.string.add_new_password
        RecordFormType.BANK_CARD -> R.string.add_new_card
        RecordFormType.SECURE_NOTE -> R.string.add_new_note
    }
    val formState by vaultFormViewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            RecordFormTopBar(
                modifier = Modifier.bottomBorder(1.dp, MaterialTheme.colorScheme.outline),
                canSave = formState.canSave,
                imageVector = Icons.Default.Close,
                title = stringResource(titleRes),
                onBack = onClose,
                onSave = onSave
            )
        }
    ) { paddingValues ->
        val contentModifier = Modifier
            .padding(paddingValues)
            .consumeWindowInsets(paddingValues)
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
            )
        when (type) {
            RecordFormType.LOGIN -> CreatePasswordScreen(
                modifier = contentModifier,
                createPasswordFormViewModel = vaultFormViewModel,
            )
            RecordFormType.BANK_CARD,
            RecordFormType.SECURE_NOTE -> Text(
                text = "TODO: ${type.key}",
                modifier = contentModifier
            )
        }
    }
}

@Preview
@Composable
fun CreateRecordScreenPreview() {
    KStoreTheme {
        /*CreateRecordScreen(
            modifier = Modifier,
            vaultFormViewModel = VaultViewModel()
        )*/
    }
}
