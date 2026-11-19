package com.vgb3.kstore.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.model.VaultFormInputs
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
    modifier: Modifier = Modifier
) {
    Form(
        modifier = modifier,
        formContent = { onInput, onSelect ->
            RecordTypePlate(
                style = styleFor("bank_card")
            )
            BankCard(
                lastFour = "3",
                expiry = "12/2",
            )

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
                                painter = painterResource(R.drawable.card_name_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.card_name_placeholder),
                                fontSize = 16.sp
                            )
                        }
                    )
                    InputField(
                        value = "1234 5678 9012 3456",
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
                                painter = painterResource(R.drawable.card_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.card_number_placeholder),
                                fontSize = 16.sp
                            )
                        }
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        //TODO заметил что икноки в InputField размером меньше, чем в полях на экране PasswordScreen - надо проверить и поправить
                        InputField(
                            modifier = Modifier.weight(1f),
                            value = "12/28",
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
                                    painter = painterResource(R.drawable.card_expiry_icon),
                                    contentDescription = "",
                                )
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.card_expiry_placeholder),
                                    fontSize = 16.sp
                                )
                            }
                        )

                        InputField(
                            modifier = Modifier.weight(1f),
                            value = "123",
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
                                    //modifier = Modifier.size(18.dp),
                                    painter = painterResource(R.drawable.card_lock_icon),
                                    contentDescription = "",
                                )
                            },
                            placeholder = {
                                Text(
                                    //TODO - какой placeholder для сvv?
                                    text = stringResource(R.string.card_number_placeholder),
                                    fontSize = 16.sp
                                )
                            }
                        )
                    }

                    InputField(
                        value = "",
                        onValueChange = {  },
                        titleField = {
                            Text(
                                text = stringResource(R.string.card_holder_title),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.card_holder_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.card_holder_placeholder),
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
        onInput = { inputField, text ->

        },
        onSelect = { optionId ->

        }
    )
}

@PreviewLightDark
@Preview
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
                    canSave = true,
                    imageVector = Icons.Default.Close,
                    modifier = Modifier.bottomBorder(1.dp, MaterialTheme.colorScheme.outline),
                    onSave = {},
                    onBack = {}
                )
            }
        ) { innerPadding ->
            val topPadding = innerPadding.calculateTopPadding() + 16.dp
            val bottomPadding = innerPadding.calculateBottomPadding()
            CreateBankCardScreen(
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