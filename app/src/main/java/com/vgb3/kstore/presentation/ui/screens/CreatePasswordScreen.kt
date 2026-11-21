package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.model.VaultFormInputs
import com.vgb3.kstore.presentation.ui.widgets.DropdownField
import com.vgb3.kstore.presentation.ui.widgets.Form
import com.vgb3.kstore.presentation.ui.widgets.GroupContent
import com.vgb3.kstore.presentation.ui.widgets.InputField
import com.vgb3.kstore.presentation.ui.widgets.RecordFormTopBar
import com.vgb3.kstore.presentation.ui.widgets.RecordTypePlate
import com.vgb3.kstore.presentation.ui.widgets.bottomBorder
import com.vgb3.kstore.presentation.ui.widgets.styleFor
import com.vgb3.kstore.ui.theme.KStoreTheme

@Composable
fun CreatePasswordScreen(
    createPasswordFormViewModel: VaultFormViewModel,
    modifier: Modifier = Modifier,
) {
    val formsState by createPasswordFormViewModel.uiState.collectAsStateWithLifecycle()
    var isPasswordVisible by remember { mutableStateOf(false) }
    Form(
        modifier = modifier,
        formContent = { onInput, onSelect ->
            RecordTypePlate(
                style = styleFor("login")
            )
            GroupContent {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    val appName = formsState.input.appNameInput
                    val focusManager = LocalFocusManager.current
                    InputField(
                        value = appName,
                        onValueChange = { onInput(VaultFormInputs.APP_NAME_INPUT, it) },
                        titleField = {
                            Text(
                                text = stringResource(R.string.app_name_title),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.app_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.app_name_placeholder),
                                fontSize = 16.sp
                            )
                        }
                    )

                    val url = formsState.input.urlInput
                    InputField(
                        value = url,
                        onValueChange = { onInput(VaultFormInputs.URL_INPUT, it) },
                        titleField = {
                            Text(
                                text = stringResource(R.string.url_title),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.www_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = "https://example.com",
                                fontSize = 16.sp
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Uri,
                            imeAction = ImeAction.Next
                        )
                    )

                    val userName = formsState.input.loginInput
                    InputField(
                        value = userName,
                        onValueChange = {
                            onInput(VaultFormInputs.LOGIN_INPUT, it)
                        },
                        titleField = {
                            Text(
                                text = stringResource(R.string.login_email),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.user_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = "vgb3@gmail.com",
                                fontSize = 16.sp
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )

                    val password = formsState.input.passwordInput
                    InputField(
                        value = password,
                        onValueChange = { onInput(VaultFormInputs.PASSWORD_INPUT, it) },
                        titleField = {
                            Text(
                                text = stringResource(R.string.password),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.secure_icon),
                                contentDescription = "",
                            )
                        },
                        visualTransformation = if (isPasswordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { isPasswordVisible = !isPasswordVisible }
                            ) {
                                Icon(
                                    imageVector = if (isPasswordVisible) {
                                        Icons.Outlined.Visibility
                                    } else {
                                        Icons.Outlined.VisibilityOff
                                    },
                                    contentDescription = stringResource(
                                        if (isPasswordVisible) {
                                            R.string.hide_password
                                        } else {
                                            R.string.show_password
                                        }
                                    ),
                                )
                            }
                        },
                        placeholder = {
                            Text(
                                text = "foo-bar-foo",
                                fontSize = 16.sp
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() }
                        )
                    )

                    val selectedCategoryId = formsState.input.selectedCategoryId

                    DropdownField(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.category_folder),
                                contentDescription = "",
                            )
                        },
                        titleField = {
                            Text(
                                text = stringResource(R.string.app_category),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.no_selected),
                                fontSize = 16.sp
                            )
                        },
                        options = formsState.categories,
                        selected = selectedCategoryId ?: 0,
                        onSelect = { onSelect(it) }
                    )
                }
            }
        },
        onInput = { inputField, text ->
            createPasswordFormViewModel.onInput(inputField, text)
        },
        onSelect = { optionId ->
            createPasswordFormViewModel.onCategorySelected(optionId)
        }
    )
}

@Composable
private fun CreatePasswordData(
    modifier: Modifier = Modifier,
    onSavePassword: () -> Unit = {},
    onInput: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    Form(
        modifier = modifier,
        formContent = { onInput, onSelect ->
            RecordTypePlate(
                style = styleFor("login")
            )
            GroupContent {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {

                    var appName by remember { mutableStateOf("") }
                    InputField(
                        value = appName,
                        onValueChange = { appName = it },
                        titleField = {
                            Text(
                                text = stringResource(R.string.app_name_title),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.app_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.app_name_placeholder),
                                fontSize = 16.sp
                            )
                        }
                    )

                    var url by remember { mutableStateOf("") }
                    InputField(
                        value = url,
                        onValueChange = { url = it },
                        titleField = {
                            Text(
                                text = stringResource(R.string.url_title),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.www_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = "https://example.com",
                                fontSize = 16.sp
                            )
                        }
                    )

                    var userName by remember { mutableStateOf("") }
                    InputField(
                        value = userName,
                        onValueChange = { userName = it },
                        titleField = {
                            Text(
                                text = stringResource(R.string.login_email),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.user_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = "vgb3@gmail.com",
                                fontSize = 16.sp
                            )
                        }
                    )

                    var password by remember { mutableStateOf("") }
                    InputField(
                        value = password,
                        onValueChange = { password = it },
                        titleField = {
                            Text(
                                text = stringResource(R.string.password),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.secure_icon),
                                contentDescription = "",
                            )
                        },
                        visualTransformation = if (isPasswordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { isPasswordVisible = !isPasswordVisible }
                            ) {
                                Icon(
                                    imageVector = if (isPasswordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                                    contentDescription = stringResource(
                                        if (isPasswordVisible) R.string.hide_password
                                        else R.string.show_password
                                    ),
                                )
                            }
                        },
                        placeholder = {
                            Text(
                                text = "foo-bar-foo",
                                fontSize = 16.sp
                            )
                        }
                    )

                    DropdownField(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.category_folder),
                                contentDescription = "",
                            )
                        },
                        titleField = {
                            Text(
                                text = stringResource(R.string.app_category),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.no_selected),
                                fontSize = 16.sp
                            )
                        },
                        options = emptyList(),
                        selected = 0,
                        onSelect = {}
                    )
                }
            }
        },
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CreatePasswordScreenPreview() {
    KStoreTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            topBar = {
                RecordFormTopBar(
                    "Создать пароль",
                    canSave = true,
                    imageVector = Icons.Default.Close,
                    modifier = Modifier.bottomBorder(1.dp, MaterialTheme.colorScheme.outline),
                    onSave = {},
                    onBack = {}
                )
            }
        ) { innerPadding ->
            val topPadding = innerPadding.calculateTopPadding() + 16.dp
            val bottomPadding = innerPadding.calculateTopPadding()
            CreatePasswordData(
                modifier = Modifier.padding(
                    top = topPadding,
                    start = 16.dp,
                    bottom = bottomPadding,
                    end = 16.dp,
                )
            )
        }
    }
}