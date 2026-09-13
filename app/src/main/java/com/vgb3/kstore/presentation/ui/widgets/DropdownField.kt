package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.ui.model.DropdownOption
import com.vgb3.kstore.ui.theme.Background
import com.vgb3.kstore.ui.theme.Border
import com.vgb3.kstore.ui.theme.Pressed
import com.vgb3.kstore.ui.theme.TextPlaceholder
import com.vgb3.kstore.ui.theme.TextSecondary
import com.vgb3.kstore.ui.theme.TitleTextField
import com.vgb3.kstore.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    options: List<DropdownOption>,
    selected: Long,
    onSelect: (Long) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    titleField: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null
) {
    var expanded by remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalRippleConfiguration provides null) {
        ExposedDropdownMenuBox(
            modifier = modifier.fillMaxWidth(),
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            val inputTextRes =
                options.find { it.id == selected }
                    ?.titleRes
                    ?: R.string.category_unknown

            val arrowRotation by animateFloatAsState(
                targetValue = if (expanded) 180f else 0f,
                animationSpec = tween(200),
                label = "arrowRotation"
            )

            InputField(
                value = stringResource(inputTextRes),
                onValueChange = {},
                titleField = titleField,
                placeholder = placeholder,
                trailingIcon = {
                    Icon(
                        painterResource(R.drawable.dropdown_arrow),
                        null,
                        Modifier.rotate(arrowRotation)
                    )
                },
                leadingIcon = leadingIcon,
                modifier = Modifier.fillMaxWidth(),
                fieldModifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .padding(bottom = 4.dp),
                readOnly = true
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                shape = RoundedCornerShape(12.dp),
                containerColor = White,
                border = BorderStroke(1.dp, Border),
            ) {
                options.forEach { option ->
                    val isSelected = option.id == selected
                    val interactionSource = remember { MutableInteractionSource() }
                    val isPressed by interactionSource.collectIsPressedAsState()
                    val itemBackground by animateColorAsState(
                        targetValue = when {
                            isPressed -> Pressed
                            isSelected -> Background
                            else -> White
                        },
                        animationSpec = tween(if (isPressed) 80 else 250),
                        label = "itemBackground"
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(option.titleRes),
                                fontSize = 16.sp,
                                //fontWeight = FontWeight.W400
                            )
                        },
                        onClick = {
                            //selected = option.text
                            onSelect(option.id)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        interactionSource = interactionSource,
                        modifier = Modifier
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(itemBackground)
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DropdownFieldPreview() {
    val categories = listOf(
        DropdownOption(1, "PERSONAL", R.string.category_personal),
        DropdownOption(2, "WORK", R.string.category_work),
        DropdownOption(3, "FINANCE", R.string.category_finance),
        DropdownOption(4, "SOCIAL", R.string.category_social)
    )
    var selected by remember { mutableLongStateOf(1) }
    DropdownField(
        titleField = {
            Text(
                text = stringResource(R.string.app_category),
                fontWeight = FontWeight.W600,
                fontSize = 14.sp,
                color = TitleTextField
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.no_selected),
                color = TextPlaceholder,
                fontSize = 16.sp
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.category_folder),
                contentDescription = "",
                tint = TextSecondary
            )
        },
        options = categories,
        selected = selected,
        onSelect = { optionId ->
            selected = optionId
        }
    )
}