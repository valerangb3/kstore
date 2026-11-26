package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun BankCard(
    modifier: Modifier = Modifier,
    lastFour: String = "••••",
    holderName: String = "",
    expiry: String = "",
) {
    val shape = RoundedCornerShape(16.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline, shape)
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.bank_card_title).uppercase(),
            fontWeight = FontWeight.Medium,
            letterSpacing = (1.1).sp,
            lineHeight = 16.sp,
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(32.dp, 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.bank_chip),
                    contentDescription = null,
                    tint = KStoreTheme.colors.bankCard.tint
                )
            }
            Icon(
                painter = painterResource(R.drawable.bank_contactless),
                tint = KStoreTheme.colors.tileNeutral.tint,
                contentDescription = null,
            )
        }
        BankCardNumber(lastFour = lastFour)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 1.1.sp,
                    lineHeight = 12.sp,
                    fontSize = 9.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    text = stringResource(R.string.bank_card_holder_title).uppercase()
                )
                BankCardHolderName(holderName)
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    fontWeight = FontWeight.Medium,
                    letterSpacing = (1.1).sp,
                    lineHeight = 12.sp,
                    fontSize = 9.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    text = stringResource(R.string.bank_card_valid_period_title).uppercase()
                )
                BankCardExpiry(expiry = expiry)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun BankCardPreview() {
    KStoreTheme {
        Surface() {
            BankCard(
                lastFour = "3456",
                holderName = "Valery Karev",
                expiry = "11/28",
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun BankCardPreviewFullScreen() {
    KStoreTheme {
        val topPadding = 16.dp
        Surface (
            color = MaterialTheme.colorScheme.background
        ) {

            BankCard(
                lastFour = "3456",
                holderName = "Valery Karev",
                expiry = "11/28",
                modifier = Modifier
                    .padding(
                        top = topPadding,
                        start = 16.dp,
                        end = 16.dp,
                    )
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun BankCardEmptyPreview() {
    KStoreTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            BankCard()
        }
    }
}

@PreviewLightDark
@Composable
private fun BankCardPartialPreview() {
    KStoreTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            BankCard(
                lastFour = "3",
                expiry = "12/2",
            )
        }
    }
}