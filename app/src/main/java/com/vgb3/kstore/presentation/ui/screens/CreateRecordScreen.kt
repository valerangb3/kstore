package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.ui.model.RecordFormType
import com.vgb3.kstore.presentation.ui.widgets.RecordFormTopBar
import com.vgb3.kstore.presentation.ui.widgets.bottomBorder
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vgb3.kstore.presentation.model.RecordFormUiState

@Composable
fun CreateRecordScreen(
    type: RecordFormType,
    vaultFormViewModel: VaultFormViewModel,
    onClose: () -> Unit,
    onSave: () -> Unit,
) {
    val titleRes = when (type) {
        RecordFormType.LOGIN -> R.string.add_new_password
        RecordFormType.BANK_CARD -> R.string.add_new_card
        RecordFormType.SECURE_NOTE -> R.string.add_new_note
    }
    val formState by vaultFormViewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(type) {
        vaultFormViewModel.start(type)
    }
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
            RecordFormType.LOGIN -> {
                val loginFormState = formState.form as? RecordFormUiState.Login
                //todo: если loginFormState - null, то нужно отобразить экран с ошибкой, экран ошибок нарисуем в другой задаче
                loginFormState?.let { state ->
                    LoginForm(
                        modifier = contentModifier,
                        loginFormState = state.draftFields,
                        onInput = vaultFormViewModel::onInput,
                        onSelect = vaultFormViewModel::onCategorySelected,
                        categories = formState.categories
                    )
                }
            }
            RecordFormType.BANK_CARD -> {
                val bankCardFormState = formState.form as? RecordFormUiState.BankCard
                //todo: если bankCardFormState - null, то нужно отобразить экран с ошибкой, экран ошибок нарисуем в другой задаче
                bankCardFormState?.let { state ->
                    CreateBankCardScreen(
                        modifier = contentModifier,
                        bankCardState = state.draftFields,
                        onInput = vaultFormViewModel::onInput,
                        onSelect = vaultFormViewModel::onCategorySelected,
                        categories = formState.categories,
                        bankCardPreview = state.preview
                    )
                }
            }
            RecordFormType.SECURE_NOTE -> {
                val secureFormState = formState.form as? RecordFormUiState.SecureNote
                //todo: если secureFormState - null, то нужно отобразить экран с ошибкой, экран ошибок нарисуем в другой задаче
                secureFormState?.let { state ->
                    CreateSecureNoteScreen(
                        modifier = contentModifier,
                        secureNoteState = state.draftFields,
                        onInput = vaultFormViewModel::onInput,
                        onSelect = vaultFormViewModel::onCategorySelected,
                        categories = formState.categories
                    )
                }
            }
        }
    }
}
