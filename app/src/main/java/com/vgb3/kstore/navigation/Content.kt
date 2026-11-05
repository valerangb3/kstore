package com.vgb3.kstore.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.ui.screens.CreateRecordScreen
import com.vgb3.kstore.presentation.ui.screens.SettingsScreen
import com.vgb3.kstore.presentation.ui.screens.VaultScreen

@Composable
fun entryProvider(
    vaultViewModel: VaultViewModel,
    vaultFormViewModel: VaultFormViewModel,
    navigator: TopLevelBackStack
): (NavKey) -> NavEntry<NavKey> = entryProvider {
    entry<AppRoute.VaultList> {
        VaultScreen(
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp,
            ),
            vaultViewModel,
            onNavigateToCreate = { type -> navigator.add(AppRoute.VaultCreate(type)) },
        )
    }
    entry<AppRoute.VaultCreate> { key ->
        CreateRecordScreen(
            type = key.type,
            vaultFormViewModel = vaultFormViewModel,
            onSave = {
                vaultFormViewModel.createVault()
                navigator.removeLast()
            },
            onClose = { navigator.removeLast() }
        )
    }
    entry<AppRoute.VaultGenerator> {
        Text("Generator")
    }
    entry<AppRoute.VaultSecurity> {
        Text("Security")
    }
    entry<AppRoute.VaultSettings> {
        SettingsScreen()
    }
}