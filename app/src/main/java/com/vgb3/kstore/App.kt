package com.vgb3.kstore

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.ui.NavDisplay
import com.vgb3.kstore.navigation.Destinations
import com.vgb3.kstore.navigation.entryProvider
import com.vgb3.kstore.navigation.rememberTopLevelBackStack
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.ui.widgets.AppBottomBar

@Composable
fun App(
    modifier: Modifier = Modifier,
    vaultViewModel: VaultViewModel,
    createPasswordFormViewModel: VaultFormViewModel,
) {
    val topLevelBackStack = rememberTopLevelBackStack(
        startKey = Destinations.HOME.route,
        topLevelKeys = Destinations.entries.map { it.route }
    )

    val entries = topLevelBackStack.toEntries(
        entryProvider(
            vaultViewModel = vaultViewModel,
            navigator = topLevelBackStack,
            createPasswordFormViewModel = createPasswordFormViewModel
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