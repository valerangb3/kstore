package com.vgb3.kstore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vgb3.kstore.presentation.model.AppState
import com.vgb3.kstore.presentation.model.ScaffoldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ScaffoldItem(
    val showFab: Boolean,
    val showBottomBar: Boolean,
    val showTopBar: Boolean
)

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

    fun updateScaffoldState(scaffoldItem: ScaffoldItem) {
        _state.update { currentState ->
            currentState.copy(
                scaffoldState = currentState.scaffoldState.copy(
                    showFab = scaffoldItem.showFab,
                    showBottomBar = scaffoldItem.showBottomBar,
                    showTopBar = scaffoldItem.showTopBar
                )
            )
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