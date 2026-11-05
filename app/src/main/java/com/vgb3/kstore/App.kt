package com.vgb3.kstore

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.ui.NavDisplay
import com.vgb3.kstore.domain.model.settings.ThemeMode
import com.vgb3.kstore.navigation.Destinations
import com.vgb3.kstore.navigation.entryProvider
import com.vgb3.kstore.navigation.rememberTopLevelBackStack
import com.vgb3.kstore.presentation.AppSettingsViewModel
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.ui.widgets.AppBottomBar
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun App(
    modifier: Modifier = Modifier,
    vaultViewModel: VaultViewModel,
    vaultFormViewModel: VaultFormViewModel,
    appSettingsViewModel: AppSettingsViewModel = viewModel(factory = AppSettingsViewModel.Factory)
) {
    val themeState by appSettingsViewModel.themeMode.collectAsStateWithLifecycle()
    val darkTheme = when (themeState) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }
    KStoreTheme(darkTheme = darkTheme) {
        val topLevelBackStack = rememberTopLevelBackStack(
            startKey = Destinations.HOME.route,
            topLevelKeys = Destinations.entries.map { it.route }
        )

        val entries = topLevelBackStack.toEntries(
            entryProvider(
                vaultViewModel = vaultViewModel,
                navigator = topLevelBackStack,
                vaultFormViewModel = vaultFormViewModel
            )
        )

        val showBottomBar = Destinations.entries.any { it.route == topLevelBackStack.currentKey }

        Scaffold(
            modifier = modifier.fillMaxSize(),
            bottomBar = {
                if (showBottomBar) {
                    AppBottomBar(
                        navigator = topLevelBackStack,
                        onItemClick = { _, destination ->
                            topLevelBackStack.addTopLevel(destination.route)
                        },
                    )
                }
            }
        ) { innerPadding ->
            NavDisplay(
                modifier = Modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding),
                onBack = {
                    topLevelBackStack.removeLast()
                },
                entries = entries
            )
        }
    }
}