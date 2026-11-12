package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun BankCardNumber(
    lastFour: String,
    modifier: Modifier = Modifier
) {
    val numberStyle = MaterialTheme.typography.bodyLarge.copy(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium
    )
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "•••• •••• ••••",
            color = MaterialTheme.colorScheme.onSurface,
            style = numberStyle
        )
        Row() {
            lastFour.forEach {
                Text(
                    text = it.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = numberStyle
                )
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun BankCardNumberPreview() {
    KStoreTheme() {
        Surface() {
            BankCardNumber("abcd")
        }
    }
}