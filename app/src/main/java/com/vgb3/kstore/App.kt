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
import com.vgb3.kstore.navigation.VaultCreate
import com.vgb3.kstore.navigation.VaultList
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.model.VaultResult
import com.vgb3.kstore.presentation.ui.screens.CreatePasswordScreen
import com.vgb3.kstore.presentation.ui.screens.VaultScreen
import com.vgb3.kstore.presentation.ui.widgets.CreatePasswordFab

@Composable
fun App(
    backStack: SnapshotStateList<Any>,
    modifier: Modifier = Modifier,
    vaultViewModel: VaultViewModel,
) {
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is VaultList -> NavEntry(key) {
                    val vaultState = vaultViewModel.vaultState.collectAsStateWithLifecycle()
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        floatingActionButton = {
                            val state = vaultState.value
                            if (state is VaultResult.VaultContent && state.vaultList.isNotEmpty()) {
                                CreatePasswordFab(
                                    onClick =  {
                                        backStack.add(VaultCreate)
                                    }
                                )
                            }
                        }
                    ) { innerPadding ->
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
                            onNavigateToCreatePasswordScreen = { backStack.add(VaultCreate) },
                        )
                    }
                }
                is VaultCreate -> NavEntry(key) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding(),
                        topBar = {
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
                    ) { innerPadding ->
                        val topPadding = innerPadding.calculateTopPadding() + 16.dp
                        val bottomPadding = innerPadding.calculateTopPadding()
                        CreatePasswordScreen(
                            modifier = Modifier.padding(
                                top = topPadding,
                                start = 16.dp,
                                bottom = bottomPadding,
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
                }
                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}