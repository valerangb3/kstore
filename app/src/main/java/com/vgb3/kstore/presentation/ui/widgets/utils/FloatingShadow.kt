package com.vgb3.kstore.presentation.ui.widgets.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

fun Modifier.floatingShadow(shape: Shape): Modifier {
    val shadowColor = Color(0x1A000000)
    return this.dropShadow(
        shape = shape,
        shadow = Shadow(
            radius = 15.dp,
            spread = (-3).dp,
            color = shadowColor,
            offset = DpOffset(x = 0.dp, 10.dp)
        )
    )
        .dropShadow(
            shape = shape,
            shadow = Shadow(
                radius = 6.dp,
                spread = (-4).dp,
                color = shadowColor,
                offset = DpOffset(x = 0.dp, 4.dp)
            )
        )
}