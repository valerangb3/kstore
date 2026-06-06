package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.presentation.model.VaultFormInputs

@Composable
fun Form(
    modifier: Modifier = Modifier,
    formContent: @Composable (
        onInput: (inputField: VaultFormInputs, text: String) -> Unit
    ) -> Unit,
    formFooter: @Composable () -> Unit,
    onInput: (inputField: VaultFormInputs, text: String) -> Unit = { inputType, inputValue -> }
) {
    Column (
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        formContent(onInput)
        formFooter()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormPreview() {
    Form(
        formContent = {},
        formFooter = {}
    )
}