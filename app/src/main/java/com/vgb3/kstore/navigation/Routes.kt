package com.vgb3.kstore.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import com.vgb3.kstore.R
import kotlinx.serialization.Serializable

enum class Destinations(
    val route: AppRoute,
    @StringRes
    val label: Int,
    @DrawableRes
    val icon: Int,
    @StringRes
    val contentDescription: Int
) {
    HOME(
        route = AppRoute.VaultList,
        label = R.string.nav_item_title_home,
        icon = R.drawable.nav_home_icon,
        contentDescription = R.string.nav_item_title_home,
    ),
    /*VAULT(
        route = AppRoute.Vault,
        label = R.string.nav_item_title_vault,
        icon = R.drawable.nav_vault_icon,
        contentDescription = R.string.nav_item_title_vault,
    ),*/
    GENERATOR(
        route = AppRoute.VaultGenerator,
        label = R.string.nav_item_title_generator,
        icon = R.drawable.nav_generator_icon,
        contentDescription = R.string.nav_item_title_generator,
    ),
    SECURITY(
        route = AppRoute.VaultSecurity,
        label = R.string.nav_item_title_secure,
        icon = R.drawable.nav_security_icon,
        contentDescription = R.string.nav_item_title_secure,
    ),
    SETTINGS(
        route = AppRoute.VaultSettings,
        label = R.string.nav_item_title_settings,
        icon = R.drawable.nav_settings_icon,
        contentDescription = R.string.nav_item_title_settings,
    )

}

@Serializable
sealed interface AppRoute : NavKey {
    @Serializable
    data object VaultCreate: AppRoute
    @Serializable
    data object VaultList: AppRoute
    //data object Vault: AppRoute
    @Serializable
    data object VaultGenerator: AppRoute
    @Serializable
    data object VaultSecurity: AppRoute
    @Serializable
    data object VaultSettings: AppRoute
}