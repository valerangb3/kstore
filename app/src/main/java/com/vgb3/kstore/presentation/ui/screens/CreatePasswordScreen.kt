package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.VaultViewModel
import com.vgb3.kstore.presentation.model.VaultFormInputs
import com.vgb3.kstore.presentation.model.VaultFormResult
import com.vgb3.kstore.presentation.ui.widgets.Buttons.PrimaryButton
import com.vgb3.kstore.presentation.ui.widgets.Buttons.TransparentButton
import com.vgb3.kstore.presentation.ui.widgets.Form
import com.vgb3.kstore.presentation.ui.widgets.GroupContent
import com.vgb3.kstore.presentation.ui.widgets.InputField
import com.vgb3.kstore.ui.theme.KStoreTheme
import com.vgb3.kstore.ui.theme.SimpleButtonTextColor
import com.vgb3.kstore.ui.theme.TextPlaceholder
import com.vgb3.kstore.ui.theme.TextSecondary
import com.vgb3.kstore.ui.theme.TitleTextField
import com.vgb3.kstore.ui.theme.White

@Composable
fun CreatePasswordScreen(
    vaultViewModel: VaultViewModel,
    modifier: Modifier = Modifier,
    onSavePassword: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    val formsState by vaultViewModel.vaultFormState.collectAsStateWithLifecycle()
    if (formsState is VaultFormResult.VaultFormFields) {
        Form(
            modifier = modifier,
            formContent = { onInput ->
                GroupContent {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        //var appName by remember { mutableStateOf("") }
                        val appName = (formsState as VaultFormResult.VaultFormFields).appNameInput
                        InputField(
                            value = appName,
                            onValueChange = {
                                onInput(
                                    VaultFormInputs.APP_NAME_INPUT,
                                    it
                                )
                            },
                            titleField = {
                                Text(
                                    text = stringResource(R.string.app_name_title),
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp,
                                    color = TitleTextField
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.app_icon),
                                    contentDescription = "",
                                    tint = TextSecondary
                                )
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.app_name_placeholder),
                                    color = TextPlaceholder,
                                    fontSize = 16.sp
                                )
                            }
                        )

                        val url = (formsState as VaultFormResult.VaultFormFields).urlInput
                        InputField(
                            value = url,
                            onValueChange = {
                                onInput(
                                    VaultFormInputs.URL_INPUT,
                                    it
                                )
                            },
                            titleField = {
                                Text(
                                    text = stringResource(R.string.url_title),
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp,
                                    color = TitleTextField
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.www_icon),
                                    contentDescription = "",
                                    tint = TextSecondary
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "https://example.com",
                                    color = TextPlaceholder,
                                    fontSize = 16.sp
                                )
                            }
                        )

                        val userName = (formsState as VaultFormResult.VaultFormFields).loginInput
                        InputField(
                            value = userName,
                            onValueChange = {
                                onInput(
                                    VaultFormInputs.LOGIN_INPUT,
                                    it
                                )
                            },
                            titleField = {
                                Text(
                                    text = stringResource(R.string.login_email),
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp,
                                    color = TitleTextField
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.user_icon),
                                    contentDescription = "",
                                    tint = TextSecondary
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "vgb3@gmail.com",
                                    color = TextPlaceholder,
                                    fontSize = 16.sp
                                )
                            }
                        )

                        val password = (formsState as VaultFormResult.VaultFormFields).passwordInput
                        InputField(
                            value = password,
                            onValueChange = {
                                onInput(
                                    VaultFormInputs.PASSWORD_INPUT,
                                    it
                                )
                            },
                            titleField = {
                                Text(
                                    text = stringResource(R.string.password),
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp,
                                    color = TitleTextField
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.secure_icon),
                                    contentDescription = "",
                                    tint = TextSecondary
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.visible),
                                    contentDescription = "",
                                    tint = TextSecondary
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "foo-bar-foo",
                                    color = TextPlaceholder,
                                    fontSize = 16.sp
                                )
                            }
                        )
                    }
                }
            },
            formFooter = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PrimaryButton(
                        onClick = onSavePassword,
                        modifier = Modifier.fillMaxWidth(),
                        content = {
                            Text(
                                text = stringResource(R.string.save_password),
                                fontSize = 16.sp,
                                color = White,
                            )
                        }
                    )
                    TransparentButton(
                        onClick = onCancel,
                        modifier = Modifier.fillMaxWidth(),
                        content = {
                            Text(
                                text = stringResource(R.string.cancel),
                                fontSize = 16.sp,
                                color = SimpleButtonTextColor
                            )
                        }
                    )
                }
            },
            onInput = { inputField, text ->
                vaultViewModel.onInput(inputField, text)
            }
        )
    }
}

@Composable
private fun CreatePasswordData(
    modifier: Modifier = Modifier,
    onSavePassword: () -> Unit = {},
    onInput: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    Form(
        modifier = modifier,
        formContent = {
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
                                color = TitleTextField
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.app_icon),
                                contentDescription = "",
                                tint = TextSecondary
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.app_name_placeholder),
                                color = TextPlaceholder,
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
                                color = TitleTextField
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.www_icon),
                                contentDescription = "",
                                tint = TextSecondary
                            )
                        },
                        placeholder = {
                            Text(
                                text = "https://example.com",
                                color = TextPlaceholder,
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
                                color = TitleTextField
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.user_icon),
                                contentDescription = "",
                                tint = TextSecondary
                            )
                        },
                        placeholder = {
                            Text(
                                text = "vgb3@gmail.com",
                                color = TextPlaceholder,
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
                                color = TitleTextField
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.secure_icon),
                                contentDescription = "",
                                tint = TextSecondary
                            )
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.visible),
                                contentDescription = "",
                                tint = TextSecondary
                            )
                        },
                        placeholder = {
                            Text(
                                text = "foo-bar-foo",
                                color = TextPlaceholder,
                                fontSize = 16.sp
                            )
                        }
                    )
                }
            }
        },
        formFooter = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PrimaryButton(
                    onClick = onSavePassword,
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        Text(
                            text = stringResource(R.string.save_password),
                            fontSize = 16.sp,
                            color = White,
                        )
                    }
                )
                TransparentButton(
                    onClick = onCancel,
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        Text(
                            text = stringResource(R.string.cancel),
                            fontSize = 16.sp,
                            color = SimpleButtonTextColor
                        )
                    }
                )
            }
        }
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        modifier = Modifier.size(40.dp),
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.left_arrow_icon),
                            contentDescription = null,
                        )
                    }
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = stringResource(R.string.add_new_password),
                        textAlign = TextAlign.Center
                    )
                }
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