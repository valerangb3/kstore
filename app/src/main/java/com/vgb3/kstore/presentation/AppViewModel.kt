package com.vgb3.kstore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vgb3.kstore.presentation.model.AppState
import com.vgb3.kstore.presentation.model.ScaffoldState
import com.vgb3.kstore.presentation.model.ScaffoldVisibleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class ScaffoldItem {
    TOP_BAR, BOTTOM_BAR, FAB;
}

class AppViewModel : ViewModel() {
    private val _state = MutableStateFlow(
        AppState(
            scaffoldState = ScaffoldState(
                showTopBar = false,
                showBottomBar = false,
                showFab = false
            )
        )
    )
    val appState = _state.asStateFlow()

    fun updateScaffoldState(scaffoldItem: ScaffoldItem, value: Boolean) {
        _state.update { currentState ->
            when (scaffoldItem) {
                ScaffoldItem.FAB -> {
                    currentState.copy(
                        scaffoldState = currentState.scaffoldState.copy(
                            showFab = value,
                        )
                    )
                }
                ScaffoldItem.TOP_BAR -> {
                    currentState.copy(
                        scaffoldState = currentState.scaffoldState.copy(
                            showTopBar = value,
                        )
                    )
                }
                ScaffoldItem.BOTTOM_BAR -> {
                    currentState.copy(
                        scaffoldState = currentState.scaffoldState.copy(
                            showBottomBar = value,
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