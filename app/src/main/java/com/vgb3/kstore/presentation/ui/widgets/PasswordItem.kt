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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.domain.model.output.VaultItem
import com.vgb3.kstore.domain.model.output.VaultItemSummary
import com.vgb3.kstore.ui.theme.Border
import com.vgb3.kstore.ui.theme.Red
import com.vgb3.kstore.ui.theme.TextPlaceholder
import com.vgb3.kstore.ui.theme.White

@Composable
fun PasswordItem(
    vaultItem: VaultItemSummary,
    modifier: Modifier = Modifier,
    onAction: () -> Unit = {},
    onPasswordCopy: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(82.dp)
            .dropShadow(
                shape = RoundedCornerShape(24.dp),
                shadow = Shadow(
                    radius = 16.dp,
                    spread = 0.dp,
                    color = Color(0x0A000000),
                    offset = DpOffset(x = 0.dp, y = 4.dp)
                )
            )
            .clip(RoundedCornerShape(24.dp))
            .background(White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(48.dp)
                .border(
                    width = 1.dp,
                    color = Border,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            //TODO: ICON NOT IMPLEMENTED
            Icon(
                painter = painterResource(R.drawable.youtube_icon),
                contentDescription = null,
                tint = Red
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            vaultItem as VaultItem.VaultLogin
            Text(
                maxLines = 1,
                text = vaultItem.title,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                maxLines = 1,
                text = vaultItem.login,
                overflow = TextOverflow.Ellipsis
            )
        }
        Row {
            Box(
                modifier = Modifier.size(32.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.copy),
                        contentDescription = null,
                        tint = TextPlaceholder
                    )
                }
            }
            Box(
                modifier = Modifier.size(32.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(R.drawable.more_horizontal),
                        contentDescription = null,
                        tint = TextPlaceholder
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PasswordItemPreview() {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PasswordItem(
            VaultItemSummary(
                id = 1,
                title = "youtube",
                subTitle = "vgb3@gmail.com",
                isFavorite = false,
                categoryId = 0,
                createdAt = 1L,
                updatedAt = 1L
            )
        )
        PasswordItem(
            VaultItemSummary(
                id = 1,
                title = "youtube",
                subTitle = "vgb3@gmail.com",
                isFavorite = false,
                categoryId = 0,
                createdAt = 1L,
                updatedAt = 1L
            )
        )
    }
}