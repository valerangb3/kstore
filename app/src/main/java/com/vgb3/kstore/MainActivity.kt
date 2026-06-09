package com.vgb3.kstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.vgb3.kstore.navigation.VaultList
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.ui.theme.KStoreTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val vaultViewModel: VaultViewModel by viewModels { VaultViewModel.Factory }
    private val vaultFormViewModel: VaultFormViewModel by viewModels { VaultFormViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KStoreTheme {
                val backStack = remember { mutableStateListOf<Any>(VaultList) }
                App(
                    backStack = backStack,
                    vaultViewModel = vaultViewModel
                )
            }
        }
    }
}