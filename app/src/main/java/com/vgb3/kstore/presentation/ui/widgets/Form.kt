package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Form(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) { }
}


@Preview
@Composable
fun FormPreview() {

}