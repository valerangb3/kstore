package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.ui.widgets.utils.floatingShadow
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun BankCardCompact(
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
            .floatingShadow(shape)
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(R.drawable.bank_chip_compact),
                contentDescription = null,
                tint = KStoreTheme.colors.bankCard.tint
            )
            BankCardNumber(lastFour = lastFour)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BankCardHolderName(
                holderName = holderName,
                modifier = Modifier.weight(1f)
            )
            BankCardExpiry(expiry)
        }
    }
}

@Preview
@Composable
private fun BankCardCompactPreview() {
    KStoreTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            BankCardCompact(
                lastFour = "3",
                expiry = "12/2",
            )
        }
    }
}

@Preview(name = "Порядок модификаторов", widthDp = 320, showBackground = true)
@Composable
private fun BankCardModifierOrderPreview() {
    KStoreTheme {
        val shape = RoundedCornerShape(16.dp)
        val color = MaterialTheme.colorScheme.surfaceVariant
        val shadow = Shadow(
            radius = 10.dp,
            color = Color.Black.copy(alpha = 0.35f),
            spread = 0.dp,
            offset = DpOffset(x = 0.dp, y = 4.dp),
        )
        val base = Modifier.fillMaxWidth().height(60.dp)

        val samples = listOf(
            "dropShadow → clip → background" to base
                .dropShadow(shape, shadow)
                .clip(shape)
                .background(color),

            "clip → background → dropShadow" to base
                .clip(shape)
                .background(color)
                .dropShadow(shape, shadow),

            "background → dropShadow → clip" to base
                .background(color)
                .dropShadow(shape, shadow)
                .clip(shape),
        )

        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            samples.forEach { (label, modifier) ->
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(label, style = MaterialTheme.typography.bodySmall)
                    Box(modifier = modifier, contentAlignment = Alignment.Center) {
                        Text("•••• •••• 3456")
                    }
                }
            }
        }
    }
}