package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.ui.widgets.Buttons.PrimaryButton
import com.vgb3.kstore.ui.theme.PrimaryIconTint
import com.vgb3.kstore.ui.theme.Success
import com.vgb3.kstore.ui.theme.White

@Composable
fun EmptyVault(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        EmptyVaultData(
            mainIcon = {
                Icon(
                    painter = painterResource(R.drawable.account_secure),
                    contentDescription = null,
                    tint = PrimaryIconTint
                )
            },
            borderTopEndCircleContent = {
                Icon(
                    painter = painterResource(R.drawable.success_check_mark),
                    contentDescription = null,
                    tint = Success
                )
            },
            borderBottomStartContent = {
                Icon(
                    painter = painterResource(R.drawable.key_icon),
                    contentDescription = null,
                    tint = PrimaryIconTint
                )
            }
        )
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 24.sp,
            text = "Ваше хранилище паролей пустое",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W700
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            text = "Сохраните первый пароль, чтобы она всегда был под рукой",
        )
        Spacer(
            modifier = Modifier.height(40.dp)
        )
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
                        text = "Создать пароль",
                        fontSize = 16.sp,
                        color = White,
                    )
                }
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmptyVaultPreview() {
    EmptyVault()
}