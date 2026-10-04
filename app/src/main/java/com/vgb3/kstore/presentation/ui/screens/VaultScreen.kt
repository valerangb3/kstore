package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vgb3.kstore.R
import com.vgb3.kstore.domain.model.output.VaultItemSummary
import com.vgb3.kstore.presentation.AppViewModel
import com.vgb3.kstore.presentation.ScaffoldItem
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.model.VaultResult
import com.vgb3.kstore.presentation.ui.widgets.CreatePasswordFab
import com.vgb3.kstore.presentation.ui.widgets.DeleteVaultItemDialog
import com.vgb3.kstore.presentation.ui.widgets.EmptyVault
import com.vgb3.kstore.presentation.ui.widgets.PasswordItem
import com.vgb3.kstore.ui.theme.Danger
import com.vgb3.kstore.ui.theme.KStoreTheme
import com.vgb3.kstore.ui.theme.White
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe
import de.charlex.compose.rememberRevealState


@Composable
fun VaultContent(
    modifier: Modifier = Modifier,
    vaultScreenState: VaultResult,
    onNavigateToCreatePasswordScreen: () -> Unit = {},
    onDeleteRequest: (VaultItemSummary) -> Unit
) {
    when (vaultScreenState) {
        is VaultResult.Idle -> {}
        is VaultResult.Error -> {}
        is VaultResult.Loading -> {}
        is VaultResult.VaultContent -> {
            VaultData(
                modifier = modifier,
                vaultItems = vaultScreenState.vaultList,
                onNavigateToCreatePasswordScreen = onNavigateToCreatePasswordScreen,
                onDeleteRequest = onDeleteRequest
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
    val pendingDelete by vaultViewModel.pendingDelete.collectAsStateWithLifecycle()

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
        vaultScreenState = vaultState,
        onDeleteRequest = vaultViewModel::onDeleteRequest
    )

    pendingDelete?.let { item ->
        DeleteVaultItemDialog(
            itemTitle = item.title,
            onConfirm = vaultViewModel::onDeleteConfirm,
            onDismiss = vaultViewModel::onDeleteDismiss,
        )
    }
    /*DeleteVaultItemDialog(
        itemTitle = "f",
        onConfirm = vaultViewModel::onDeleteConfirm,
        onDismiss = vaultViewModel::onDeleteDismiss,
    )*/
}

@Composable
fun VaultData(
    modifier: Modifier = Modifier,
    vaultItems: List<VaultItemSummary>,
    onNavigateToCreatePasswordScreen: () -> Unit = {},
    onDeleteRequest: (VaultItemSummary) -> Unit = {}
) {
    if (vaultItems.isNotEmpty()) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(vaultItems, key = { it.id }) { vaultItem ->
                RevealSwipe(
                    shape = RoundedCornerShape(24.dp),
                    closeOnContentClick = true,
                    state = rememberRevealState(directions = setOf(RevealDirection.EndToStart)),
                    backgroundCardEndColor = Danger,
                    onBackgroundEndClick = {
                        onDeleteRequest(vaultItem)
                        true
                    },
                    hiddenContentEnd = {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                            tint = White
                        )
                    },
                    backgroundCardStartColor = MaterialTheme.colorScheme.tertiaryContainer,
                    backgroundStartActionLabel = "Mark entry as favorite",//todo
                    backgroundEndActionLabel = stringResource(R.string.delete),
                    card = { shape, content ->
                        Card(
                            modifier = Modifier.matchParentSize(),
                            shape = shape,
                            colors = CardDefaults.cardColors(
                                contentColor = MaterialTheme.colorScheme.onSecondary,
                                containerColor = Color.Transparent
                            ),
                            content = content
                        )
                    }
                ) { shape ->
                    PasswordItem(
                        modifier = Modifier.fillMaxWidth(),
                        shape = shape,
                        vaultItem = vaultItem,
                        onAction = {},
                        onPasswordCopy = {}
                    )
                }
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
                    VaultItemSummary(
                        id = 1,
                        title = "youtube",
                        subTitle = "vgb3@gmail.com",
                        isFavorite = true,
                        categoryId = 1,
                        createdAt = 1,
                        updatedAt = 2
                    ),
                    VaultItemSummary(
                        id = 2,
                        title = "google",
                        subTitle = "vgb3@gmail.com",
                        isFavorite = true,
                        categoryId = 1,
                        createdAt = 1,
                        updatedAt = 2
                    )
                )
            )
        }
    }
}