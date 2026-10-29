package com.vgb3.kstore.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class RecordTypeColors(val tint: Color, val container: Color)

@Immutable
data class KStoreColors(
    val labelText: Color,
    val placeholderText: Color,
    val success: Color,
    val login: RecordTypeColors,
    val bankCard: RecordTypeColors,
    val secureNote: RecordTypeColors,
    val tileAmber: RecordTypeColors,
    val tileIndigo: RecordTypeColors,
    val tileNeutral: RecordTypeColors,
    val accentText: Color,
)

val LightKStoreColors = KStoreColors(
    labelText = Slate700,
    placeholderText = Slate400,
    success = Green500,
    login = RecordTypeColors(Indigo600, Indigo600.copy(alpha = 0.10f)),
    bankCard = RecordTypeColors(Green600, Green600.copy(alpha = 0.10f)),
    secureNote = RecordTypeColors(Amber600, Amber600.copy(alpha = 0.10f)),
    tileAmber = RecordTypeColors(Amber600, Amber50),
    tileIndigo  = RecordTypeColors(Indigo600, Indigo600.copy(alpha = 0.10f)),
    tileNeutral = RecordTypeColors(Slate500, Slate100),
    accentText  = Indigo600
)

val DarkKStoreColors = KStoreColors(
    labelText = Slate300,
    placeholderText = Slate500,
    success = Green400,
    login = RecordTypeColors(Indigo400, Indigo400.copy(alpha = 0.16f)),
    bankCard = RecordTypeColors(Green400, Green400.copy(alpha = 0.16f)),
    secureNote = RecordTypeColors(Amber400, Amber400.copy(alpha = 0.16f)),
    tileAmber = RecordTypeColors(Amber400, Amber400.copy(alpha = 0.16f)),
    tileIndigo  = RecordTypeColors(Indigo400, Indigo400.copy(alpha = 0.16f)),
    tileNeutral = RecordTypeColors(Slate400, Slate700),   // не Slate800: это цвет фона диалога
    accentText  = Indigo400,
)

val LocalKStoreColors = staticCompositionLocalOf { LightKStoreColors }