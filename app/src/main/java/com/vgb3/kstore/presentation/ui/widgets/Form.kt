package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.presentation.model.VaultFormInputs
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun Form(
    modifier: Modifier = Modifier,
    formContent: @Composable (
        onInput: (inputField: VaultFormInputs, text: String) -> Unit,
        onSelect: (optionId: Long) -> Unit
    ) -> Unit,
    formFooter: @Composable () -> Unit,
    onInput: (inputField: VaultFormInputs, text: String) -> Unit = { _, _ -> },
    onSelect: (optionId: Long) -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    Column (
        modifier = modifier
            .fillMaxHeight()
            .imePadding()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        formContent(onInput, onSelect)
        Spacer(Modifier.weight(1f))
        formFooter()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormPreview() {
    KStoreTheme {
        Form(
            formContent = { onInput, onSelect -> },
            formFooter = {}
        )
    }
}