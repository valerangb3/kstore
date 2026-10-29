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
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Contrast
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.ui.model.ThemeOptionStyle
import com.vgb3.kstore.ui.theme.KStoreTheme
import java.nio.file.WatchEvent

enum class ThemeMode {
    SYSTEM, LIGHT, DARK;
}

@Composable
fun ThemeMode.style(): ThemeOptionStyle {
    val c = KStoreTheme.colors
    return when (this) {
        ThemeMode.SYSTEM -> ThemeOptionStyle(
            R.string.theme_system,
            R.string.theme_system_hint,
            Icons.Outlined.Contrast,
            c.tileNeutral
        )
        ThemeMode.LIGHT -> ThemeOptionStyle(
            R.string.theme_light,
            R.string.theme_light_hint,
            Icons.Outlined.LightMode,
            c.tileAmber
        )
        ThemeMode.DARK -> ThemeOptionStyle(
            R.string.theme_dark,
            R.string.theme_dark_hint,
            Icons.Outlined.DarkMode,
            c.tileIndigo
        )
    }
}

@Composable
private fun ThemeOption(
    mode: ThemeMode,
    selected: Boolean,
    onClick: () -> Unit
) {
    val cs = MaterialTheme.colorScheme
    val style = mode.style()
    val shape = RoundedCornerShape(12.dp)

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .border(
                1.dp,
                if (selected)
                    cs.primary
                else
                    cs.outline,
                shape
            )
            .background(
                if (selected)
                    cs.primaryContainer.copy(alpha = 0.5f)
                else
                    Color.Transparent,
            )
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            Modifier
                .size(40.dp)
                .background(
                    style.colors.container,
                    shape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                style.icon,
                modifier = Modifier.size(20.dp),
                contentDescription = null,
                tint = style.colors.tint,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                stringResource(style.title),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = cs.onSurface
            )
            Text(
                stringResource(style.hint),
                style = MaterialTheme.typography.bodySmall,
                color = cs.onSurfaceVariant
            )
        }
        if (selected) {
            Icon(
                Icons.Outlined.Check,
                contentDescription = null,
                tint = cs.primary
            )
        }
    }
}