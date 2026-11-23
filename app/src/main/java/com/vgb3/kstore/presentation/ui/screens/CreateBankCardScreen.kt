package com.vgb3.kstore.presentation.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.VaultFormViewModel
import com.vgb3.kstore.presentation.ui.widgets.BankCard
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
fun CreateBankCardScreen(
    createRecordFormViewModel: VaultFormViewModel,
    modifier: Modifier = Modifier
) {
    var isCodeVisible by remember { mutableStateOf(false) }
    Form(
        modifier = modifier,
        formContent = { onInput, onSelect ->
            RecordTypePlate(
                style = styleFor("bank_card")
            )
            BankCard()

            GroupContent {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    InputField(
                        value = "",
                        onValueChange = { /*todo onInput(VaultFormInputs.APP_NAME_INPUT, it)*/ },
                        titleField = {
                            Text(
                                text = stringResource(R.string.card_name_title),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                //modifier = Modifier.size(22.dp),
                                painter = painterResource(R.drawable.text_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.card_name_placeholder),
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                    InputField(
                        value = "",
                        onValueChange = {  },
                        titleField = {
                            Text(
                                text = stringResource(R.string.card_number_title),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(R.drawable.card_icon),
                                contentDescription = "",
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.card_number_placeholder),
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        InputField(
                            modifier = Modifier.weight(1f),
                            value = "",
                            onValueChange = {  },
                            titleField = {
                                Text(
                                    text = stringResource(R.string.card_expiry_title),
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp,
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(R.drawable.card_expiry_icon),
                                    contentDescription = "",
                                )
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.card_expiry_placeholder),
                                    fontSize = 16.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                        )

                        InputField(
                            modifier = Modifier.weight(1f),
                            visualTransformation = if (isCodeVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                            value = "",
                            onValueChange = {  },
                            titleField = {
                                Text(
                                    text = stringResource(R.string.card_cvv_title),
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp,
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(R.drawable.card_lock_icon),
                                    contentDescription = "",
                                )
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.NumberPassword
                            ),
                            trailingIcon = {
                                IconButton(onClick = { isCodeVisible = !isCodeVisible }) {
                                    Icon(
                                        imageVector = if (isCodeVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                                        contentDescription = stringResource(
                                            if (isCodeVisible) R.string.hide_cvv
                                            else R.string.show_cvv
                                        ),
                                    )
                                }
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.card_cvv_placeholder),
                                    fontSize = 16.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        )
                    }

                    InputField(
                        value = "",
                        onValueChange = {  },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        titleField = {
                            Text(
                                text = stringResource(R.string.card_holder_title),
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
                                text = stringResource(R.string.card_holder_placeholder),
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
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
        onInput = { inputField, text ->

        },
        onSelect = { optionId ->

        }
    )
}

@Preview(
    name = "390 dp — светлая",
    widthDp = 390,
    heightDp = 800,
    showSystemUi = true,
    fontScale = 1f,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "390 dp — тёмная",
    widthDp = 390,
    heightDp = 800,
    showSystemUi = true,
    fontScale = 1f,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CreateBankCardScreenPreview() {
    KStoreTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            topBar = {
                RecordFormTopBar(
                    "Новая карта",
                    canSave = false,
                    imageVector = Icons.Default.Close,
                    modifier = Modifier.bottomBorder(1.dp, MaterialTheme.colorScheme.outline),
                    onSave = {},
                    onBack = {}
                )
            }
        ) { innerPadding ->
            val topPadding = innerPadding.calculateTopPadding() + 16.dp
            val bottomPadding = innerPadding.calculateBottomPadding()
            /*CreateBankCardScreen(
                modifier = Modifier.padding(
                    top = topPadding,
                    start = 16.dp,
                    bottom = bottomPadding,
                    end = 16.dp,
                )
            )*/
        }
    }
}