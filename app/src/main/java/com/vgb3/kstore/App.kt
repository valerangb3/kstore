package com.vgb3.kstore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.vgb3.kstore.navigation.AppRoute
import com.vgb3.kstore.presentation.AppViewModel
import com.vgb3.kstore.presentation.ScaffoldItem
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.model.ScaffoldVisibleState
import com.vgb3.kstore.presentation.model.VaultResult
import com.vgb3.kstore.presentation.ui.screens.CreatePasswordScreen
import com.vgb3.kstore.presentation.ui.screens.VaultScreen
import com.vgb3.kstore.presentation.ui.widgets.CreatePasswordFab

@Composable
fun App(
    backStack: SnapshotStateList<AppRoute>,
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel,
    vaultViewModel: VaultViewModel,
) {
    val appState by appViewModel.appState.collectAsStateWithLifecycle()
    val first = backStack.last()

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
                Text("Bottom bar")
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    is AppRoute.VaultList -> NavEntry(key) {
                        val vaultState by vaultViewModel.vaultState.collectAsStateWithLifecycle()

                        val showFab = if (vaultState is VaultResult.VaultContent) {
                            (vaultState as VaultResult.VaultContent).vaultList.isNotEmpty()
                        } else false

                        appViewModel.updateScaffoldState(
                            ScaffoldItem(
                                showTopBar = false,
                                showBottomBar = !appViewModel.appState.value.scaffoldState.showBottomBar,
                                showFab = showFab
                            )
                        )
                        Column() {
                            Button(onClick = {
                                appViewModel.updateScaffoldState(
                                    ScaffoldItem(
                                        showTopBar = false,
                                        showBottomBar = !appViewModel.appState.value.scaffoldState.showBottomBar,
                                        showFab = showFab
                                    )
                                )
                            }) {
                                Text("Показать/скрыть нижнюю навигацию")
                            }
                            VaultScreen(
                                modifier = Modifier.padding(
                                    start = 24.dp,
                                    end = 24.dp,
                                ),
                                //vaultViewModel = vaultViewModel,
                                vaultScreenState = vaultState,
                                onNavigateToCreatePasswordScreen = { backStack.add(AppRoute.VaultCreate) },
                            )
                        }
                    }
                    is AppRoute.VaultCreate -> NavEntry(key) {
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
                            onCancel = {
                                backStack.removeLastOrNull()
                                vaultViewModel.clearFormFields()
                            },
                            onSavePassword = {
                                vaultViewModel.createVault()
                                backStack.removeLastOrNull()
                            },
                            vaultViewModel = vaultViewModel,
                        )
                    }
                    else -> {
                        error("Unknown route: $key")
                    }
                }
            }
        )
    }
}