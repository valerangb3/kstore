package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.ui.widgets.SettingsGroup
import com.vgb3.kstore.presentation.ui.widgets.SettingsItem
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SettingsGroup(
            title = stringResource(R.string.settings_item_theme_appearance_title)
        ) {
            item {
                SettingsItem(
                    title = stringResource(R.string.settings_item_theme_title),
                    subTitle = stringResource(R.string.settings_item_theme_sub_title),
                    icon = Icons.Outlined.DarkMode,
                    iconTint = KStoreTheme.colors.tileAmber.tint,
                    iconContainer = KStoreTheme.colors.tileAmber.container,
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}