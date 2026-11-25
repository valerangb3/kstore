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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.ui.widgets.utils.bankCardTextTransition
import com.vgb3.kstore.ui.theme.KStoreTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun BankCardHolderName(
    holderName: String,
    modifier: Modifier = Modifier
) {
    val displayHolderName = holderName.ifBlank { stringResource(R.string.bank_card_name_placeholder) }
    val incomingName = displayHolderName.uppercase()
    var debouncedName by remember { mutableStateOf(incomingName) }
    LaunchedEffect(incomingName) {
        delay(120L.milliseconds)
        debouncedName = incomingName
    }

    AnimatedContent(
        modifier = modifier,
        targetState = debouncedName,
        label = "NameHolder",
        transitionSpec = { bankCardTextTransition() }
    ) { displayedHolderName ->
        Text(
            text = displayedHolderName,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 16.sp,
            fontSize = 12.sp,
            maxLines = 1,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.3.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@PreviewLightDark
@Preview
@Composable
fun BankCardHolderNamePreview() {
    KStoreTheme {
        Surface {
            var holderName by remember { mutableStateOf("Valery Karev") }
            Column {
                BankCardHolderName(
                    holderName = holderName,
                    modifier = Modifier.width(200.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    mapOf(
                        "Default" to "Имя фамилия",
                        "Developer" to "Valery Karev",
                        "Long name" to "Aleksandr Vladimirovich Ivanod"
                    ).forEach {
                        Button(
                            onClick = { holderName = it.value }
                        ) {
                            Text(it.key)
                        }
                    }
                }
            }
        }
    }
}