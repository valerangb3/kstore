package com.vgb3.kstore.presentation.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.ui.theme.Background
import com.vgb3.kstore.ui.theme.Border
import com.vgb3.kstore.ui.theme.TextPlaceholder
import com.vgb3.kstore.ui.theme.TextSecondary
import com.vgb3.kstore.ui.theme.TitleTextField
import com.vgb3.kstore.ui.theme.White


@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    titleField: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false,
    singleLine: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            titleField?.invoke()
            OutlinedTextField(
                singleLine = singleLine,
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                placeholder = placeholder,
                shape = RoundedCornerShape(12.dp),
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Border,
                    unfocusedBorderColor = Border,
                    focusedContainerColor = White,
                    unfocusedContainerColor = Background,
                ),
                textStyle = TextStyle(
                    fontSize = 16.sp
                ),
                readOnly = readOnly
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun InputFieldPreview() {
    var appName by remember { mutableStateOf("") }
    InputField(
        value = appName,
        titleField = {
            Text(
                text = "App name",
                fontWeight = FontWeight.W600,
                fontSize = 14.sp,
                color = TitleTextField
            )
        },
        onValueChange = { appName = it },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = "",
                tint = TextSecondary
            )
        },
        placeholder = {
            Text(
                text = "e.g. Netflix, GitHub",
                color = TextPlaceholder,
                fontSize = 16.sp
            )
        }
    )
}