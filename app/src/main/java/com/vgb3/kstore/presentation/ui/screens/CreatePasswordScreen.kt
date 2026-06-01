package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CreatePasswordScreen(
    modifier: Modifier = Modifier
) {
    var appName by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = appName,
            onValueChange = { appName = it }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CreatePasswordScreenPreview() {
    CreatePasswordScreen()
}