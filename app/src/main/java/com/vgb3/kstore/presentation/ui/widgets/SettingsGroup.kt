package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.ui.theme.KStoreTheme

class SettingsGroupScope {
    internal val items = mutableListOf<@Composable () -> Unit>()

    fun item(content: @Composable () -> Unit) {
        items += content
    }
}

@Composable
fun SettingsGroup(
    modifier: Modifier = Modifier,
    title: String? = null,
    content: SettingsGroupScope.() -> Unit
) {
    val items = SettingsGroupScope().apply(content).items
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        title?.let {
            Text(
                text = it.uppercase(),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp,
                color = KStoreTheme.colors.placeholderText,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            items.forEachIndexed { index, item ->
                if (index > 0) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                }
                item()
            }
        }
    }
}

@Preview
@Composable
fun SettingsGroupPreview() {
    KStoreTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            SettingsGroup(
                title = stringResource(R.string.settings_item_theme_appearance_title)
            ) {
                item {
                    SettingsItem(
                        title = stringResource(R.string.settings_item_theme_title),
                        subTitle = stringResource(R.string.settings_item_theme_sub_title),
                        icon = Icons.Outlined.DarkMode,
                        iconTint = KStoreTheme.colors.tileAmber.tint,
                        iconContainer = KStoreTheme.colors.tileAmber.container,
                        onClick = {}
                    )
                }
                item {
                    SettingsItem(
                        title = stringResource(R.string.settings_item_theme_title),
                        subTitle = stringResource(R.string.settings_item_theme_sub_title),
                        icon = Icons.Outlined.DarkMode,
                        iconTint = KStoreTheme.colors.tileAmber.tint,
                        iconContainer = KStoreTheme.colors.tileAmber.container,
                        onClick = {}
                    )
                }
            }
        }
    }
}