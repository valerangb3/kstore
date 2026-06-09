package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.ui.theme.Border
import com.vgb3.kstore.ui.theme.TextPlaceholder
import com.vgb3.kstore.ui.theme.TextSecondary
import com.vgb3.kstore.ui.theme.TitleTextField
import com.vgb3.kstore.ui.theme.White

@Composable
fun GroupContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val boxShape = RoundedCornerShape(16.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Border,
                shape = boxShape
            )
            .dropShadow(
                shape = boxShape,
                shadow = Shadow(
                    radius = 10.dp,
                    color = Color(0x0000000D),
                    spread = 0.dp,
                    offset = DpOffset(x = 0.dp, y = 1.dp)
                )
            )
            .background(
                color = White,
                shape = boxShape
            )

            .padding(24.dp)
    ) {
        content()
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GroupContentPreview() {
    GroupContent {
        var appName by remember { mutableStateOf("") }
        InputField(
            value = appName,
            titleField = {
                Text(
                    text = "App name",
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp,
                    color = TitleTextField
                )
            },
            onValueChange = { appName = it },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.app_icon),
                    contentDescription = "",
                    tint = TextSecondary
                )
            },
            placeholder = {
                Text(
                    text = "e.g. Netflix, GitHub",
                    color = TextPlaceholder,
                    fontSize = 16.sp
                )
            }
        )
    }
}