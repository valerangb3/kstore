package com.vgb3.kstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.vgb3.kstore.navigation.AppRoute
import com.vgb3.kstore.navigation.Destinations
import com.vgb3.kstore.presentation.AppViewModel
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.ui.theme.KStoreTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val vaultViewModel: VaultViewModel by viewModels { VaultViewModel.Factory }
    //private val vaultFormViewModel: VaultFormViewModel by viewModels { VaultFormViewModel.Factory }
    private val appViewModel: AppViewModel by viewModels { AppViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KStoreTheme {
                App(
                    vaultViewModel = vaultViewModel,
                    appViewModel = appViewModel,
                )
            }
        }
    }
}