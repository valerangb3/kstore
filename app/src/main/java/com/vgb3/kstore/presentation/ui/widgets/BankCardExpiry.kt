package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.ui.widgets.utils.bankCardTextTransition
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun BankCardExpiry(
    expiry: String,
    modifier: Modifier = Modifier
) {
    val expiryStyle = MaterialTheme.typography.bodyLarge.copy(
        lineHeight = 16.sp,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.3.sp,
        color = MaterialTheme.colorScheme.onSurface
    )
    val monthPlaceholder = stringResource(R.string.bank_card_month_placeholder)
    val yearPlaceholder = stringResource(R.string.bank_card_year_placeholder)
    val monthSymbols = ("0123456789$monthPlaceholder")
        .uppercase()
        .toSet()

    val yearSymbols = ("0123456789$yearPlaceholder")
        .uppercase()
        .toSet()

    val parts = expiry.split("/", limit = 2)
    val month = parts
        .getOrNull(0)
        .orEmpty()
        .take(2)
        .padEnd(2, monthPlaceholder.first())
    val year = parts
        .getOrNull(1)
        .orEmpty()
        .take(2)
        .padEnd(2, yearPlaceholder.first())

    val textMeasurer = rememberTextMeasurer()

    val monthCellWidthPx = remember(textMeasurer, expiryStyle, monthSymbols) {
        monthSymbols.maxOf { symbol ->
            textMeasurer.measure(
                text = symbol.toString(),
                style = expiryStyle,
                maxLines = 1,
                softWrap = false
            ).size.width
        }
    }

    val yearCellWidthPx = remember(textMeasurer, expiryStyle, yearSymbols) {
        yearSymbols.maxOf { symbol ->
            textMeasurer.measure(
                text = symbol.toString(),
                style = expiryStyle,
                maxLines = 1,
                softWrap = false
            ).size.width
        }
    }

    val monthCellWidth = with(LocalDensity.current) {
        monthCellWidthPx.toDp()
    }

    val yearCellWidth = with(LocalDensity.current) {
        yearCellWidthPx.toDp()
    }
    Row(modifier = modifier) {
        AnimatedContent(
            targetState = month.uppercase(),
            label = "",
            transitionSpec = { bankCardTextTransition() }
        ) { displayedMonth ->
            Row {
                displayedMonth.forEach { symbol ->
                    Text(
                        modifier = Modifier.width(monthCellWidth),
                        text = symbol.toString(),
                        style = expiryStyle,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
        Text(
            text = "/",
            style = expiryStyle
        )
        AnimatedContent(
            targetState = year.uppercase(),
            label = "",
            transitionSpec = { bankCardTextTransition() }
        ) { displayedYear ->
            Row {
                displayedYear.forEach { symbol ->
                    Text(
                        modifier = Modifier.width(yearCellWidth),
                        text = symbol.toString(),
                        style = expiryStyle,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BankCardExpiryPreview() {
    KStoreTheme {
        Surface {
            var monthHolder by remember { mutableStateOf("мм") }
            var yearHolder by remember { mutableStateOf("гг") }
            Column(modifier = Modifier.padding(top = 36.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                BankCardExpiry(expiry = "$monthHolder/$yearHolder")
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    mapOf(
                        "Пусто" to listOf("мм", "гг"),
                        "Начало месяца" to listOf("1м", "гг"),
                        "Месяц заполнен" to listOf("12", "гг"),
                        "Начало года" to listOf("12", "2г"),
                        "Заполнено" to listOf("12", "28"),
                        "Другой год" to listOf("12", "29"),
                        "Другой месяц" to listOf("11", "29"),
                    ).forEach {
                        Button(
                            onClick = {
                                monthHolder = it.value[0]
                                yearHolder = it.value[1]
                            }
                        ) {
                            Text(it.key)
                        }
                    }
                }
            }
        }
    }
}