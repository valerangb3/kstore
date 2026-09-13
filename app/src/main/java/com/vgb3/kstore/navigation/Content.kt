package com.vgb3.kstore.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import com.vgb3.kstore.presentation.AppViewModel
import com.vgb3.kstore.presentation.ScaffoldItem
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.ui.screens.CreatePasswordScreen
import com.vgb3.kstore.presentation.ui.screens.VaultScreen

@Composable
fun entryProvider(
    appViewModel: AppViewModel,
    vaultViewModel: VaultViewModel,
    createPasswordFormViewModel: VaultFormViewModel,
    backStack: NavBackStack<NavKey>
): (NavKey) -> NavEntry<NavKey> = entryProvider {
    entry<AppRoute.VaultList> {
        VaultScreen(
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp,
            ),
            appViewModel,
            vaultViewModel,
            onNavigateToCreatePasswordScreen = { backStack.add(AppRoute.VaultCreate) },
        )
    }
    entry<AppRoute.VaultCreate> {
        appViewModel.updateScaffoldState(
            ScaffoldItem(
                showTopBar = true,
                showBottomBar = false,
                showFab = false
            )
        )
        CreatePasswordScreen(
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
            ),
            onCancel = { backStack.removeLastOrNull() },
            onSavePassword = {
                createPasswordFormViewModel.createVault()
                backStack.removeLastOrNull()
            },
            createPasswordFormViewModel = createPasswordFormViewModel,
        )
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