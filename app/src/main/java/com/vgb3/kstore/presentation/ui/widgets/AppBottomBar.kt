package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier
) {
    BottomAppBar() { }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppBottomBarPreview() {
    AppBottomBar()
}