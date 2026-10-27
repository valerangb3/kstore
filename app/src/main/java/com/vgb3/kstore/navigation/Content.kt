package com.vgb3.kstore.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.ui.screens.CreatePasswordScreen
import com.vgb3.kstore.presentation.ui.screens.VaultScreen
import com.vgb3.kstore.presentation.ui.widgets.BackTopBar

@Composable
fun entryProvider(
    vaultViewModel: VaultViewModel,
    createPasswordFormViewModel: VaultFormViewModel,
    navigator: TopLevelBackStack
): (NavKey) -> NavEntry<NavKey> = entryProvider {
    entry<AppRoute.VaultList> {
        VaultScreen(
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp,
            ),
            vaultViewModel,
            onNavigateToCreatePasswordScreen = { navigator.add(AppRoute.VaultCreate) },
        )
    }
    entry<AppRoute.VaultCreate> {
        Scaffold(
            topBar = {
                BackTopBar(
                    title = stringResource(R.string.add_new_password),
                    onBack = { navigator.removeLast() }
                )
            }
        ) { paddingValues ->
            CreatePasswordScreen(
                modifier = Modifier
                    .padding(paddingValues)
                    .consumeWindowInsets(paddingValues)
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    ),
                onCancel = { navigator.removeLast() },
                onSavePassword = {
                    createPasswordFormViewModel.createVault()
                    navigator.removeLast()
                },
                createPasswordFormViewModel = createPasswordFormViewModel,
            )
        }
    }
    entry<AppRoute.VaultGenerator> {
        Text("Generator")
    }
    entry<AppRoute.VaultSecurity> {
        Text("Security")
    }
    entry<AppRoute.VaultSettings> {
        Text("Settings")
    }
}