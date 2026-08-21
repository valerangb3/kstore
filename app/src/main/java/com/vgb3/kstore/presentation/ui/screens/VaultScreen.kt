package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vgb3.kstore.domain.model.output.VaultItemSummary
import com.vgb3.kstore.presentation.AppViewModel
import com.vgb3.kstore.presentation.ScaffoldItem
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.model.VaultResult
import com.vgb3.kstore.presentation.ui.widgets.CreatePasswordFab
import com.vgb3.kstore.presentation.ui.widgets.EmptyVault
import com.vgb3.kstore.presentation.ui.widgets.PasswordItem
import com.vgb3.kstore.ui.theme.KStoreTheme


@Composable
fun VaultContent(
    modifier: Modifier = Modifier,
    vaultScreenState: VaultResult,
    onNavigateToCreatePasswordScreen: () -> Unit = {},
) {
    when (vaultScreenState) {
        is VaultResult.Idle -> {}
        is VaultResult.Error -> {}
        is VaultResult.Loading -> {}
        is VaultResult.VaultContent -> {
            VaultData(
                modifier = modifier,
                vaultItems = vaultScreenState.vaultList,
                onNavigateToCreatePasswordScreen = onNavigateToCreatePasswordScreen
            )
        }
    }
}

@Composable
fun VaultScreen(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel,
    vaultViewModel: VaultViewModel,
    onNavigateToCreatePasswordScreen: () -> Unit = {},
) {
    val vaultState by vaultViewModel.vaultState.collectAsStateWithLifecycle()

    val showFab = if (vaultState is VaultResult.VaultContent) {
        (vaultState as VaultResult.VaultContent).vaultList.isNotEmpty()
    } else false

    appViewModel.updateScaffoldState(
        ScaffoldItem(
            showTopBar = false,
            showBottomBar = showFab,
            showFab = showFab
        )
    )
    VaultContent(
        modifier = modifier,
        onNavigateToCreatePasswordScreen = onNavigateToCreatePasswordScreen,
        vaultScreenState = vaultState
    )
}

@Composable
fun VaultData(
    modifier: Modifier = Modifier,
    vaultItems: List<VaultItemSummary>,
    onNavigateToCreatePasswordScreen: () -> Unit = {},
) {
    if (vaultItems.isNotEmpty()) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(vaultItems) { vaultItem ->
                PasswordItem(
                    vaultItem = vaultItem,
                    onAction = {},
                    onPasswordCopy = {}
                )
            }
        }
    } else {
        EmptyVault(
            onNavigateToCreatePasswordScreen = onNavigateToCreatePasswordScreen
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun VaultScreenPreview() {
    KStoreTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                CreatePasswordFab(
                    onClick =  {}
                )
            }
        ) { innerPadding ->
            val topPadding = innerPadding.calculateTopPadding()
            val bottomPadding = innerPadding.calculateTopPadding()
            VaultData(
                modifier = Modifier.padding(
                    top = topPadding,
                    start = 24.dp,
                    bottom = bottomPadding,
                    end = 24.dp,
                ),
                vaultItems = listOf(
                    /*VaultItem(
                        id = "#1",
                        appName = "youtube",
                        url = "",
                        login = "vgb3@gmail.com",
                        password = "password,"
                    ),
                    VaultItem(
                        id = "#2",
                        appName = "google",
                        url = "",
                        login = "vgb3@gmail.com",
                        password = "password,"
                    )*/
                )
            )
        }
    }
}