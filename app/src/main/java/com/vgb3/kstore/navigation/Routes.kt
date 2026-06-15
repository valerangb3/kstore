package com.vgb3.kstore.navigation

sealed interface AppRoute {
    data object VaultList: AppRoute
    data object VaultCreate: AppRoute
}