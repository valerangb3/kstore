package com.vgb3.kstore.presentation.ui.widgets.utils

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith

fun bankCardTextTransition(): ContentTransform {
    val durationMillis = 180

    val enter = fadeIn(tween(durationMillis)) +
            slideInVertically(tween(durationMillis)) { height ->
                height / 5
            }

    val exit = fadeOut(tween(durationMillis)) +
            slideOutVertically(tween(durationMillis)) { height ->
                -height / 5
            }
    return enter togetherWith exit
}