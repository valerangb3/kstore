package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vgb3.kstore.R
import com.vgb3.kstore.domain.model.output.VaultItemSummary
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.model.VaultResult
import com.vgb3.kstore.presentation.ui.model.RecordTypeUi
import com.vgb3.kstore.presentation.ui.widgets.CreateMenuBottomSheet
import com.vgb3.kstore.presentation.ui.widgets.CreatePasswordFab
import com.vgb3.kstore.presentation.ui.widgets.DeleteVaultItemDialog
import com.vgb3.kstore.presentation.ui.widgets.EmptyVault
import com.vgb3.kstore.presentation.ui.widgets.PasswordItem
import com.vgb3.kstore.presentation.ui.widgets.RecordTypeButton
import com.vgb3.kstore.presentation.ui.widgets.styleFor
import com.vgb3.kstore.ui.theme.Danger
import com.vgb3.kstore.ui.theme.KStoreTheme
import com.vgb3.kstore.ui.theme.White
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe
import de.charlex.compose.rememberRevealState
import kotlinx.coroutines.launch


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VaultScreen(
    modifier: Modifier = Modifier,
    vaultViewModel: VaultViewModel,
    onNavigateToCreatePasswordScreen: () -> Unit = {},
) {
    val recordTypes by vaultViewModel.recordTypes.collectAsStateWithLifecycle()
    val vaultState by vaultViewModel.vaultState.collectAsStateWithLifecycle()
    val pendingDelete by vaultViewModel.pendingDelete.collectAsStateWithLifecycle()

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val showFab = (vaultState as? VaultResult.VaultContent)?.vaultList?.isNotEmpty() == true


    Scaffold(
        floatingActionButton = {
            if (showFab) {
                CreatePasswordFab(
                    onClick =  {
                        showBottomSheet = true
                    }
                )
            }
        }
    ) { paddingValues ->
        VaultContent(
            modifier = modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues),
            onNavigateToCreatePasswordScreen = onNavigateToCreatePasswordScreen,
            vaultScreenState = vaultState,
            onDeleteRequest = vaultViewModel::onDeleteRequest
        )
    }

    val clickHandler: (RecordTypeUi) -> Unit = {
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                showBottomSheet = false
            }
        }
    }

    pendingDelete?.let { item ->
        DeleteVaultItemDialog(
            itemTitle = item.title,
            onConfirm = vaultViewModel::onDeleteConfirm,
            onDismiss = vaultViewModel::onDeleteDismiss,
        )
    }

    if (showBottomSheet) {
        CreateMenuBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                showBottomSheet = false
            },
        ) {
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = "Новая запись",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Выберите, что сохранить",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(14.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    recordTypes.forEach { recordType ->
                        RecordTypeButton(
                            recordType = recordType,
                            style = styleFor(recordType.type),
                        ) {
                            clickHandler(it)
                        }
                    }
                }
            }
        }
    }
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