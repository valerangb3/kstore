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
import com.vgb3.kstore.presentation.VaultViewModel
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
    val vaultState = vaultViewModel.vaultState.collectAsStateWithLifecycle()
    //todo: detect recomposition, need fix ()
    //todo: need pass an object so that the viewmodel gets a list of elements
    //todo: but its make extra request to db
    //todo: !!!possible fix is cache in repository!!!
    val appState = appViewModel.appState.collectAsStateWithLifecycle()
    val lastRoute = backStack.last()
    Scaffold(
        modifier = modifier
            //.statusBarsPadding() - if VaultCreate screen
            .fillMaxSize(),
        floatingActionButton = {
            if (lastRoute is AppRoute.VaultList) {
                CreatePasswordFab(
                    onClick =  {
                        backStack.add(AppRoute.VaultCreate)
                    }
                )
            }
            /*val state = vaultState.value
            if (state is VaultResult.VaultContent && state.vaultList.isNotEmpty()) {
                CreatePasswordFab(
                    onClick =  {
                        backStack.add(AppRoute.VaultCreate)
                    }
                )
            }*/
        },
        topBar = {
            if (lastRoute is AppRoute.VaultCreate) {
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
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryProvider = { key ->
                when (key) {
                    is AppRoute.VaultList -> NavEntry(key) {
                        VaultScreen(
                            modifier = Modifier.padding(
                                start = 24.dp,
                                end = 24.dp,
                            ),
                            vaultViewModel = vaultViewModel,
                            onNavigateToCreatePasswordScreen = { backStack.add(AppRoute.VaultCreate) },
                        )
                    }
                    is AppRoute.VaultCreate -> NavEntry(key) {
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


        if (false) {
            val topPadding = innerPadding.calculateTopPadding()
            val bottomPadding = innerPadding.calculateTopPadding()
            VaultScreen(
                modifier = Modifier.padding(
                    top = topPadding,
                    start = 24.dp,
                    bottom = bottomPadding,
                    end = 24.dp,
                ),
                vaultViewModel = vaultViewModel,
                onNavigateToCreatePasswordScreen = { backStack.add(AppRoute.VaultCreate) },
            )
        }
    }
}