package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vgb3.kstore.R
import com.vgb3.kstore.domain.model.settings.ThemeMode
import com.vgb3.kstore.presentation.AppSettingsViewModel
import com.vgb3.kstore.presentation.settings.SettingsViewModel
import com.vgb3.kstore.presentation.ui.widgets.ScreenTitle
import com.vgb3.kstore.presentation.ui.widgets.SettingsGroup
import com.vgb3.kstore.presentation.ui.widgets.SettingsItem
import com.vgb3.kstore.presentation.ui.widgets.ThemeDialog
import com.vgb3.kstore.presentation.ui.widgets.style
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsScreenViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory),
    appSettingsViewModel: AppSettingsViewModel = viewModel(factory = AppSettingsViewModel.Factory)
) {
    val appThemeState by appSettingsViewModel.themeMode.collectAsStateWithLifecycle()
    SettingsContent(
        modifier = modifier,
        themeMode = appThemeState,
        onThemeSelect = { settingsScreenViewModel.onThemeSelected(it) }
    )
}

@Composable
private fun SettingsContent(
    themeMode: ThemeMode,
    onThemeSelect: (ThemeMode) -> Unit,
    modifier: Modifier = Modifier
) {
    var showThemeDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        ScreenTitle(
            text = stringResource(R.string.settings_screen_title),
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            SettingsGroup(
                title = stringResource(R.string.settings_item_theme_appearance_title)
            ) {
                item {
                    val style = themeMode.style()
                    SettingsItem(
                        title = stringResource(R.string.settings_item_theme_title),
                        subTitle = stringResource(style.title),
                        icon = style.icon,
                        iconTint = style.colors.tint,
                        iconContainer = style.colors.container,
                        onClick = { showThemeDialog = true }
                    )
                }
            }
        }
    }

    if (showThemeDialog) {
        ThemeDialog(
            current = themeMode,
            onSelect = {
                onThemeSelect(it)
                showThemeDialog = false
            },
            onDismiss = { showThemeDialog = false }
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    KStoreTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SettingsContent(
                themeMode = ThemeMode.SYSTEM,
                onThemeSelect = {}
            )
        }
    }
}