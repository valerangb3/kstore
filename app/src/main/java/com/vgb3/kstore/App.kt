package com.vgb3.kstore

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.vgb3.kstore.navigation.AppRoute
import com.vgb3.kstore.navigation.Destinations
import com.vgb3.kstore.navigation.TopLevelBackStack
import com.vgb3.kstore.navigation.entryProvider
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.ui.widgets.AppBottomBar

@Composable
fun App(
    modifier: Modifier = Modifier,
    vaultViewModel: VaultViewModel,
    createPasswordFormViewModel: VaultFormViewModel,
) {
    val topLevelBackStack = remember { TopLevelBackStack(Destinations.HOME.route) }

    val entryProvide = entryProvider(
        vaultViewModel = vaultViewModel,
        navigator = topLevelBackStack,
        createPasswordFormViewModel = createPasswordFormViewModel
    )
    val decorators = listOf(rememberSaveableStateHolderNavEntryDecorator<AppRoute>())
    val decoratedEntries = rememberDecoratedNavEntries(
        backStack = topLevelBackStack.backStack,
        entryDecorators = decorators,
        entryProvider = entryProvide
    )

    val currentKey = topLevelBackStack.backStack.last()
    val showBottomBar = Destinations.entries.any { it.route == currentKey }

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
            entries = decoratedEntries
        )
    }
}