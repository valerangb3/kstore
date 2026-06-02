package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vgb3.kstore.R

@Composable
fun CreatePasswordScreen(
    modifier: Modifier = Modifier
) {
    var appName by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("App name")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = appName,
                onValueChange = { appName = it },
                placeholder = { Text("Input here") },
                shape = RoundedCornerShape(12.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.app_icon),
                        contentDescription = ""
                    )
                }
            )
        }
        /*BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            onValueChange = {},
            decorationBox = { innerTextField ->
                innerTextField()
            }
        )*/
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CreatePasswordScreenPreview() {
    CreatePasswordScreen()
}