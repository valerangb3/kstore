package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.R

@Composable
fun BackTopBar(
    title: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            modifier = Modifier.size(40.dp),
            onClick = onBack
        ) {
            Icon(
                painter = painterResource(R.drawable.left_arrow_icon),
                contentDescription = null,
            )
        }
        Text(
            modifier = Modifier
                .weight(1f),
            text = title,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun BackTopBarPreview() {
    BackTopBar(
        "Create",
        {}
    )
}