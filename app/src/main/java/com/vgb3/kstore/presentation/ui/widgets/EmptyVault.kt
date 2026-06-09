package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun EmptyVault(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(56.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.shield),
            contentDescription = null
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
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
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmptyVaultPreview() {
    EmptyVault()
}