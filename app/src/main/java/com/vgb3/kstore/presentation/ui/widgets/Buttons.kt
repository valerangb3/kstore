package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.ui.widgets.Buttons.TransparentButton
import com.vgb3.kstore.presentation.ui.widgets.Buttons.PrimaryButton
import com.vgb3.kstore.ui.theme.Primary
import com.vgb3.kstore.ui.theme.SimpleButtonTextColor
import com.vgb3.kstore.ui.theme.White

object Buttons {
    @Composable
    fun TransparentButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        content: @Composable RowScope.() -> Unit,
    ) {
        Button(
            modifier = modifier.height(56.dp),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            enabled = enabled,
            content = content,
        )
    }

    @Composable
    fun PrimaryButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        content: @Composable RowScope.() -> Unit,
        leadingIcon: (@Composable () -> Unit)? = null,
    ) {
        Button(
            modifier = modifier.height(56.dp),
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary
            ),
            enabled = enabled,
            shape = RoundedCornerShape(12.dp),
            content = content
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SimpleButtonPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        PrimaryButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(14.dp),
                        contentDescription = null,
                        painter = painterResource(R.drawable.plus)
                    )
                    Text(
                        text = "Save Password",
                        fontSize = 16.sp,
                        color = White,
                    )
                }
            },
            leadingIcon = {

            }
        )
        TransparentButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            content = {
                Text(
                    text = "Cancel",
                    fontSize = 16.sp,
                    color = SimpleButtonTextColor
                )
            }
        )
    }
}