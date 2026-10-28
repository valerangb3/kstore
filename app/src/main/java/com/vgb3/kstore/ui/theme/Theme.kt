
package com.vgb3.kstore.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Indigo600,
    onPrimary = White,               // текст/иконка НА кнопке primary
    primaryContainer = Indigo50,     // RecordTypeButton нажат
    background = Slate50,            // фон Scaffold
    onBackground = Slate900,
    surface = White,                 // карточки, поля, нижняя панель, шит
    onSurface = Slate900,            // основной текст
    surfaceVariant = Slate100,       // "нажато" в DropdownField
    onSurfaceVariant = Slate500,     // второстепенный текст, иконки полей
    surfaceContainer = White,        // фон DropdownMenu
    surfaceContainerHigh = White,    // фон AlertDialog
    surfaceContainerHighest = White,
    outline = Slate200,              // обводки
    error = Red500,
    onError = White,
)

private val DarkColorScheme = darkColorScheme(
    primary = Indigo500,
    onPrimary = White,
    primaryContainer = Indigo950,
    background = Slate950,
    onBackground = Slate50,
    surface = Slate900,
    onSurface = Slate50,
    surfaceVariant = Slate800,
    onSurfaceVariant = Slate400,
    surfaceContainer = Slate800,
    surfaceContainerHigh = Slate800,
    surfaceContainerHighest = Slate800,
    outline = Slate700,
    error = Red500,
    onError = White,
)

@Composable
fun KStoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val kstoreColors = if (darkTheme) DarkKStoreColors else LightKStoreColors

    CompositionLocalProvider(LocalKStoreColors provides kstoreColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object KStoreTheme {
    val colors: KStoreColors
        @Composable @ReadOnlyComposable
        get() = LocalKStoreColors.current
}