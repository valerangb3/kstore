package com.vgb3.kstore.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R

val InterFontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
)

private val default = Typography()

val Typography = Typography(
    displayLarge = default.displayLarge.copy(fontFamily = InterFontFamily),
    displayMedium = default.displayMedium.copy(fontFamily = InterFontFamily),
    displaySmall = default.displaySmall.copy(fontFamily = InterFontFamily),

    headlineLarge = default.headlineLarge.copy(fontFamily = InterFontFamily),
    headlineMedium = default.headlineMedium.copy(fontFamily = InterFontFamily),
    headlineSmall = default.headlineSmall.copy(fontFamily = InterFontFamily),

    bodyLarge = default.bodyLarge.copy(fontFamily = InterFontFamily),
    bodyMedium = default.bodyMedium.copy(fontFamily = InterFontFamily),
    bodySmall = default.bodySmall.copy(fontFamily = InterFontFamily),

    labelLarge = default.labelLarge.copy(fontFamily = InterFontFamily),
    labelMedium = default.labelMedium.copy(fontFamily = InterFontFamily),
    labelSmall = default.labelSmall.copy(fontFamily = InterFontFamily)
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)