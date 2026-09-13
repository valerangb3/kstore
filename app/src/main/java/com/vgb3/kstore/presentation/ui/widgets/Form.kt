package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vgb3.kstore.presentation.model.VaultFormInputs

@Composable
fun Form(
    modifier: Modifier = Modifier,
    formContent: @Composable (
        onInput: (inputField: VaultFormInputs, text: String) -> Unit,
        onSelect: (optionId: Long) -> Unit
    ) -> Unit,
    formFooter: @Composable () -> Unit,
    onInput: (inputField: VaultFormInputs, text: String) -> Unit = { inputType, inputValue -> },
    onSelect: (optionId: Long) -> Unit = {}
) {
    Column (
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        formContent(onInput, onSelect)
        formFooter()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormPreview() {
    Form(
        formContent = { onInput, onSelect -> },
        formFooter = {}
    )
}