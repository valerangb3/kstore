package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.presentation.ui.widgets.Form
import com.vgb3.kstore.presentation.ui.widgets.RecordFormTopBar
import com.vgb3.kstore.presentation.ui.widgets.RecordTypePlate
import com.vgb3.kstore.presentation.ui.widgets.bottomBorder
import com.vgb3.kstore.presentation.ui.widgets.styleFor
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun CreateBankCardScreen(
    modifier: Modifier = Modifier
) {
    Form(
        modifier = modifier,
        formContent = { onInput, onSelect ->
            RecordTypePlate(
                style = styleFor("bank_card")
            )
        },
        onInput = { inputField, text ->

        },
        onSelect = { optionId ->

        }
    )
}

@PreviewLightDark
@Composable
fun CreateBankCardScreenPreview() {
    KStoreTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            topBar = {
                RecordFormTopBar(
                    "Новая карта",
                    canSave = true,
                    imageVector = Icons.Default.Close,
                    modifier = Modifier.bottomBorder(1.dp, MaterialTheme.colorScheme.outline),
                    onSave = {},
                    onBack = {}
                )
            }
        ) { innerPadding ->
            val topPadding = innerPadding.calculateTopPadding() + 16.dp
            val bottomPadding = innerPadding.calculateTopPadding()
            CreateBankCardScreen(
                modifier = Modifier.padding(
                    top = topPadding,
                    start = 16.dp,
                    bottom = bottomPadding,
                    end = 16.dp,
                )
            )
        }
    }
}