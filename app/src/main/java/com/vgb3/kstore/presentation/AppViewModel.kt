package com.vgb3.kstore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vgb3.kstore.navigation.AppRoute
import com.vgb3.kstore.presentation.model.AppState
import com.vgb3.kstore.presentation.model.ScaffoldState
import com.vgb3.kstore.presentation.model.VisibleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _state = MutableStateFlow<AppState>(AppState.Idle)
    val appState = _state.asStateFlow()

    //TODO after impl delete init block
    init {
        updateScaffoldState(
            item = VisibleState()
        )
    }

    fun updateScaffoldState(item: VisibleState) {
        _state.update { currentState ->
            when (currentState) {
                is AppState.State -> {
                    currentState.copy(
                        scaffoldState = currentState.scaffoldState.copy(
                            showFab = item.fabVisible,
                            showBottomBar = item.bottomBarVisible,
                            showTopBar = item.topBarVisible
                        )
                    )
                }
                is AppState.Idle -> {
                    AppState.State(
                        ScaffoldState(
                            currentRoute = AppRoute.VaultList,
                            showTopBar = true,
                            showBottomBar = true,
                            showFab = false
                        )
                    )
                }
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                AppViewModel()
            }
        }
    }
}