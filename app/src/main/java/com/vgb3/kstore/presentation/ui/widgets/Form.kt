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

@Composable
fun Form(
    modifier: Modifier = Modifier,
    formContent: @Composable () -> Unit,
    formFooter: @Composable () -> Unit
) {
    Column (
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        formContent()
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