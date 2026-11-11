package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.ui.theme.KStoreTheme

fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = this.drawBehind {
    val thickness = strokeWidth.toPx()
    val y = size.height - thickness / 2
    drawLine(
        color = color,
        start = Offset(0f, y),
        end = Offset(size.width, y),
        strokeWidth = thickness
    )
}

@Composable
fun RecordFormTopBar(
    title: String,
    imageVector: ImageVector,
    canSave: Boolean,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            modifier = Modifier.size(40.dp),
            onClick = onBack
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
        Text(
            modifier = Modifier
                .weight(1f),
            fontWeight = FontWeight.Bold,
            text = title,
            textAlign = TextAlign.Center
        )
        TextButton(
            onClick = onSave,
            contentPadding = PaddingValues(0.dp),
            enabled = canSave
        ) {
            Text(
                text = stringResource(R.string.save),
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun RecordFormTopBarPreview() {
    KStoreTheme {
        Surface() {
            RecordFormTopBar(
                "Create",
                canSave = true,
                imageVector = Icons.Default.Close,
                modifier = Modifier.bottomBorder(1.dp, MaterialTheme.colorScheme.outline),
                onSave = {},
                onBack = {}
            )
        }
    }
}