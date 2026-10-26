package com.vgb3.kstore

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.ui.theme.KStoreTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val vaultViewModel: VaultViewModel by viewModels { VaultViewModel.Factory }
    private val vaultFormViewModel: VaultFormViewModel by viewModels { VaultFormViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )
        setContent {
            KStoreTheme {
                App(
                    vaultViewModel = vaultViewModel,
                    createPasswordFormViewModel = vaultFormViewModel
                )
            }
        }
    }
}