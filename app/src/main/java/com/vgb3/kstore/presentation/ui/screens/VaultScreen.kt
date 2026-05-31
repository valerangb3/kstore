package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.presentation.ui.widgets.CreatePasswordFab
import com.vgb3.kstore.presentation.ui.widgets.PasswordItem
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun VaultScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PasswordItem()
            PasswordItem()
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun VaultScreenPreview() {
    KStoreTheme() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                CreatePasswordFab(
                    onClick =  {}
                )
            }
        ) { innerPadding ->
            val topPadding = innerPadding.calculateTopPadding()
            val bottomPadding = innerPadding.calculateTopPadding()
            VaultScreen(
                modifier = Modifier.padding(
                    top = topPadding,
                    start = 24.dp,
                    bottom = bottomPadding,
                    end = 24.dp,
                )
            )
        }
    }
}