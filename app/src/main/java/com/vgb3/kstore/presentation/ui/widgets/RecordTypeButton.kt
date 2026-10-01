package com.vgb3.kstore.presentation.ui.widgets

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.R
import com.vgb3.kstore.ui.theme.Amber600
import com.vgb3.kstore.ui.theme.Green600
import com.vgb3.kstore.ui.theme.Indigo600
import com.vgb3.kstore.ui.theme.KStoreIcons
import com.vgb3.kstore.ui.theme.KStoreTheme
import com.vgb3.kstore.ui.theme.Slate100
import com.vgb3.kstore.ui.theme.Slate400
import com.vgb3.kstore.ui.theme.Slate500

data class RecordTypeStyle(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    val icon: ImageVector,
    val tint: Color,
    val container: Color
)

fun styleFor(key: String) = when (key) {
    "login" -> RecordTypeStyle(
        R.string.type_login,
        R.string.type_login_sub,
        KStoreIcons.Login,
        Indigo600,
        Indigo600.copy(alpha = .1f)
    )
    "bank_card" -> RecordTypeStyle(
        R.string.type_card,
        R.string.type_card_sub,
        KStoreIcons.BankCard,
        Green600,
        Green600.copy(alpha = .1f)
    )
    "secure_note" -> RecordTypeStyle(
        R.string.type_note,
        R.string.type_note_sub,
        KStoreIcons.SecureNote,
        Amber600,
        Amber600.copy(alpha = .1f)
    )
    else -> RecordTypeStyle(
        R.string.type_unknown,
        R.string.type_unknown_sub,
        KStoreIcons.UnknownNote,
        Slate500,
        Slate100
    )
}

@Composable
fun RecordTypeButton(
    style: RecordTypeStyle,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val cs = MaterialTheme.colorScheme
    Surface(
        interactionSource = interaction,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = if (pressed) cs.primaryContainer.copy(alpha = .5f) else cs.surface,
        border = BorderStroke(1.dp, if (pressed) cs.primary else cs.outline)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(style.container, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = style.icon,
                    contentDescription = null,
                    tint = style.tint
                )
            }
            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Text(
                    text = stringResource(style.title),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = cs.onSurface
                )
                Text(
                    text = stringResource(style.subtitle),
                    style = MaterialTheme.typography.bodySmall,
                    color = cs.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = if (pressed) cs.primary else Slate400,
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewRecordTypeButton() {
    Column {
        KStoreTheme() {
            RecordTypeButton(styleFor("login")) {  }
            RecordTypeButton(styleFor("bank_card")) {  }
            RecordTypeButton(styleFor("secure_note")) {  }
        }
    }
}