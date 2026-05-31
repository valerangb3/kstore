package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.R
import com.vgb3.kstore.ui.theme.Primary
import com.vgb3.kstore.ui.theme.White

@Composable
fun CreatePasswordFab(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier
            .size(56.dp),
        shape = CircleShape,
        onClick = onClick,
        containerColor = Primary
    ) {
        Icon(
            painter = painterResource(R.drawable.plus),
            contentDescription = null,
            tint = White
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CreatePasswordFabPreview() {
    CreatePasswordFab(
        onClick = {}
    )
}