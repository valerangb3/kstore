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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun BankCard(
    modifier: Modifier = Modifier
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
        BankCardNumber(lastFour = "3456")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
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
                Text(
                    text = "Имя фамилия".uppercase(),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 16.sp,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.3.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
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
                Text(
                    text = "мм/гг".uppercase(),
                    lineHeight = 16.sp,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.3.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun BankCardPreview() {
    KStoreTheme {
        Surface() {
            BankCard()
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