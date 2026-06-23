package com.vgb3.kstore

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.vgb3.kstore.navigation.AppRoute
import com.vgb3.kstore.navigation.Destinations
import com.vgb3.kstore.navigation.entryProvider
import com.vgb3.kstore.presentation.AppViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.ui.widgets.AppBottomBar
import com.vgb3.kstore.presentation.ui.widgets.CreatePasswordFab

@Composable
fun App(
    modifier: Modifier = Modifier,
    //backStack: SnapshotStateList<AppRoute>,
    appViewModel: AppViewModel,
    vaultViewModel: VaultViewModel,
) {
    val appState by appViewModel.appState.collectAsStateWithLifecycle()
    //val startDestination = Destinations.HOME
    val backStack = rememberNavBackStack(Destinations.HOME.route)
    val first = backStack.last()
    val entryProvide = entryProvider(
        appViewModel = appViewModel,
        vaultViewModel = vaultViewModel,
        backStack = backStack
    )
    val decorators = listOf(rememberSaveableStateHolderNavEntryDecorator<NavKey>())
    val decoratedEntries = rememberDecoratedNavEntries(
        backStack = backStack,
        entryDecorators = decorators,
        entryProvider = entryProvide
    )

    Scaffold(
        modifier = modifier
            //.statusBarsPadding() - if VaultCreate screen
            .fillMaxSize()
            .then(
        if (first is AppRoute.VaultCreate)
                    Modifier.statusBarsPadding()
                else Modifier
            ),
        floatingActionButton = {
            if (appState.scaffoldState.showFab) {
                CreatePasswordFab(
                    onClick =  {
                        backStack.add(AppRoute.VaultCreate)
                    }
                )
            }
        },
        topBar = {
            if (appState.scaffoldState.showTopBar) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        modifier = Modifier.size(40.dp),
                        onClick = {
                            backStack.removeLastOrNull()
                            vaultViewModel.clearFormFields()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.left_arrow_icon),
                            contentDescription = null,
                        )
                    }
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = stringResource(R.string.add_new_password),
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        bottomBar = {
            if (appState.scaffoldState.showBottomBar) {
                AppBottomBar(
                    backStack = backStack,
                    onItemClick = { _, destination ->
                        backStack.add(destination.route)
                    },
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            //backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entries = decoratedEntries
            //entryProvider = entryProvide
        )
    }
}