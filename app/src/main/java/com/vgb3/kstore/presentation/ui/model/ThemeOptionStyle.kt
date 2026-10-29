package com.vgb3.kstore.presentation.ui.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.vgb3.kstore.ui.theme.RecordTypeColors

data class ThemeOptionStyle(
    @StringRes val title: Int,
    @StringRes val hint: Int,
    val icon: ImageVector,
    val colors: RecordTypeColors
)