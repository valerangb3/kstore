package com.vgb3.kstore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.vgb3.kstore.navigation.AppRoute
import com.vgb3.kstore.navigation.Destinations
import com.vgb3.kstore.navigation.TopLevelBackStack
import com.vgb3.kstore.navigation.entryProvider
import com.vgb3.kstore.presentation.AppViewModel
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.model.VaultResult
import com.vgb3.kstore.presentation.ui.model.RecordTypeUi
import com.vgb3.kstore.presentation.ui.widgets.AppBottomBar
import com.vgb3.kstore.presentation.ui.widgets.CreateMenuBottomSheet
import com.vgb3.kstore.presentation.ui.widgets.CreatePasswordFab
import com.vgb3.kstore.presentation.ui.widgets.RecordTypeButton
import com.vgb3.kstore.presentation.ui.widgets.styleFor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel,
    vaultViewModel: VaultViewModel,
    createPasswordFormViewModel: VaultFormViewModel,
) {
    val appState by appViewModel.appState.collectAsStateWithLifecycle()
    val vaultState by vaultViewModel.vaultState.collectAsStateWithLifecycle()
    val recordTypesState by vaultViewModel.recordTypes.collectAsStateWithLifecycle()
    val topLevelBackStack = remember { TopLevelBackStack(Destinations.HOME.route) }

    val entryProvide = entryProvider(
        appViewModel = appViewModel,
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
    val showFab = if (vaultState is VaultResult.VaultContent) {
        (vaultState as VaultResult.VaultContent).vaultList.isNotEmpty()
    } else false
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier
            //.statusBarsPadding() - if VaultCreate screen
            .fillMaxSize()
            .then(
                if (topLevelBackStack.backStack.last() is AppRoute.VaultCreate) Modifier.statusBarsPadding()
                else Modifier
            ),
        floatingActionButton = {
            if (showFab) {
                CreatePasswordFab(
                    onClick =  {
                        //topLevelBackStack.add(AppRoute.VaultCreate)
                        showBottomSheet = true
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
                            topLevelBackStack.removeLast()
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
                    navigator = topLevelBackStack,
                    onItemClick = { _, destination ->
                        topLevelBackStack.addTopLevel(destination.route)
                    },
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            onBack = { topLevelBackStack.removeLast() },
            entries = decoratedEntries
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
                    recordTypesState.forEach { recordType ->
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