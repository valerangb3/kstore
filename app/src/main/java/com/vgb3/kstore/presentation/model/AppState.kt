package com.vgb3.kstore.presentation.model

import com.vgb3.kstore.navigation.AppRoute


sealed interface AppState {
    object Idle : AppState
    data class State(
        val scaffoldState: ScaffoldState
    ): AppState
}


class VisibleState(
    val topBarVisible: Boolean = false,
    val bottomBarVisible: Boolean = false,
    val fabVisible: Boolean = false,
)
/*sealed interface VisibleState {
    class TopBarVisible(val isVisible: Boolean): VisibleState
    class BottomBarVisible(val isVisible: Boolean): VisibleState
    class FabVisible(val isVisible: Boolean): VisibleState
}*/

data class ScaffoldState(
    val currentRoute: AppRoute,
    val showTopBar: Boolean,
    val showBottomBar: Boolean,
    val showFab: Boolean
)