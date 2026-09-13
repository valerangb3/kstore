package com.vgb3.kstore.presentation.model

data class AppState(
    val scaffoldState: ScaffoldState
)

data class ScaffoldState(
    val showTopBar: Boolean,
    val showBottomBar: Boolean,
    val showFab: Boolean
)