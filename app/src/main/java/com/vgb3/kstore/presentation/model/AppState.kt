package com.vgb3.kstore.presentation.model

import com.vgb3.kstore.navigation.AppRoute


data class AppState(
    val scaffoldState: ScaffoldState
)

data class ScaffoldState(
    val showTopBar: Boolean,
    val showBottomBar: Boolean,
    val showFab: Boolean
)

class ScaffoldVisibleState(
    val topBarVisible: Boolean = false,
    val bottomBarVisible: Boolean = true,
    val fabVisible: Boolean = false,
)