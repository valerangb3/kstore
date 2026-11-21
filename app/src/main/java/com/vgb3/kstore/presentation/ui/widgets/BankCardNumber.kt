package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.presentation.ui.widgets.utils.bankCardTextTransition
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
    val textMeasurer = rememberTextMeasurer()
    val cellWidthPx = remember(textMeasurer, numberStyle) {
        "0123456789•".maxOf { symbol ->
            textMeasurer.measure(
                text = symbol.toString(),
                style = numberStyle,
                maxLines = 1,
                softWrap = false
            ).size.width
        }
    }
    val cellWidth = with(LocalDensity.current) {
        cellWidthPx.toDp()
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "•••• •••• ••••",
            color = MaterialTheme.colorScheme.onSurface,
            style = numberStyle
        )

        Row {
            lastFour.forEach { symbol ->
                AnimatedContent(
                    targetState = symbol,
                    label = "CardDigit",
                    transitionSpec = { bankCardTextTransition() }
                ) { displayedSymbol ->
                    Text(
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(cellWidth),
                        text = displayedSymbol.toString(),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = numberStyle
                    )
                }
            }
        }
    }
}


@PreviewLightDark
@Preview
@Composable
private fun BankCardNumberPreview() {
    KStoreTheme {
        Surface {
            var lastFour by remember { mutableStateOf("1234") }
            Column {
                BankCardNumber(lastFour = lastFour)
                Text("Цель: $lastFour")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("••••", "1234", "1235").forEach {
                        Button(
                            onClick = { lastFour = it }
                        ) {
                            Text(it)
                        }
                    }
                }
            }
        }
    }
}