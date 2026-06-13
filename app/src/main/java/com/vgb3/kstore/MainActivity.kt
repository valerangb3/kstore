package com.vgb3.kstore

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.vgb3.kstore.di.dagger.modules.MainModule
import com.vgb3.kstore.di.test.datasource.LocalSource
import com.vgb3.kstore.di.test.presenter.MainActivityPresenter
import com.vgb3.kstore.navigation.VaultList
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.ui.theme.KStoreTheme
import javax.inject.Inject
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val vaultViewModel: VaultViewModel by viewModels { VaultViewModel.Factory }
    private val vaultFormViewModel: VaultFormViewModel by viewModels { VaultFormViewModel.Factory }
    @Inject
    lateinit var localSource: LocalSource
    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (this.applicationContext as KStoreApplication).appComponent
        val mainComponent = appComponent
            .getMainComponent()
            .setId("foo-bar")
            .build()
        mainComponent.getUser()
        //Log.d("CHECK_PRESENTER", mainActivityPresenter.hashCode().toString())
        //dataSourceComponent.inject(this)
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