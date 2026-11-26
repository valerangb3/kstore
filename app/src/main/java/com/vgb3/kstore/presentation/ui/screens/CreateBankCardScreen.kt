package com.vgb3.kstore.presentation.ui.screens

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
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
import androidx.compose.ui.platform.LocalInspectionMode
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
import com.vgb3.kstore.presentation.model.BankCardPreviewUiState
import com.vgb3.kstore.presentation.model.RecordForm
import com.vgb3.kstore.presentation.model.VaultFormInputs
import com.vgb3.kstore.presentation.model.toPreview
import com.vgb3.kstore.presentation.ui.model.DropdownOption
import com.vgb3.kstore.presentation.ui.widgets.BankCard
import com.vgb3.kstore.presentation.ui.widgets.BankCardCompact
import com.vgb3.kstore.presentation.ui.widgets.DropdownField
import com.vgb3.kstore.presentation.ui.widgets.Form
import com.vgb3.kstore.presentation.ui.widgets.GroupContent
import com.vgb3.kstore.presentation.ui.widgets.InputField
import com.vgb3.kstore.presentation.ui.widgets.RecordFormTopBar
import com.vgb3.kstore.presentation.ui.widgets.RecordTypePlate
import com.vgb3.kstore.presentation.ui.widgets.bottomBorder
import com.vgb3.kstore.presentation.ui.widgets.styleFor
import com.vgb3.kstore.ui.theme.KStoreTheme
import kotlin.String

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateBankCardScreen(
    bankCardState: RecordForm.BankCard,
    categories: List<DropdownOption>,
    bankCardPreview: BankCardPreviewUiState,
    onInput: (VaultFormInputs, String) -> Unit,
    onSelect: (Long) -> Unit,
    modifier: Modifier = Modifier,
    isImeVisible: Boolean = WindowInsets.isImeVisible,
) {
    var isCodeVisible by remember { mutableStateOf(false) }
    val cardEnter = slideInHorizontally(animationSpec = tween(180)) { -it }
    val cardExit = slideOutHorizontally(animationSpec = tween(180)) { it }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
    ) {
        Form(
            modifier = Modifier.weight(1f),
            formContent = { onInput, onSelect ->
                Column {
                    RecordTypePlate(
                        style = styleFor("bank_card")
                    )
                    BankCard(
                        modifier = Modifier.padding(top = 24.dp),
                        holderName = bankCardPreview.holderName,
                        lastFour = bankCardPreview.lastFour,
                        expiry = bankCardPreview.expiry
                    )
                }
                GroupContent(modifier = Modifier.padding(bottom = 12.dp)) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        InputField(
                            value = bankCardState.cardTitle,
                            onValueChange = { onInput(VaultFormInputs.BankCard.TITLE, it) },
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
                            value = bankCardState.cardNumber,
                            onValueChange = { onInput(VaultFormInputs.BankCard.NUMBER, it) },
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
                                value = bankCardState.cardExpiry,
                                onValueChange = { onInput(VaultFormInputs.BankCard.EXPIRY, it) },
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
                                value = bankCardState.cardCvv,
                                onValueChange = { onInput(VaultFormInputs.BankCard.CVV, it) },
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
                            value = bankCardState.cardHolder,
                            onValueChange = { onInput(VaultFormInputs.BankCard.HOLDER, it) },
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
                            options = categories,
                            selected = bankCardState.selectedCategoryId ?: 0,
                            onSelect = onSelect,
                        )
                    }
                }
            },
            onInput = onInput,
            onSelect = onSelect
        )
        AnimatedVisibility(
            modifier = Modifier.padding(8.dp),
            visible = isImeVisible,
            enter = cardEnter,
            exit = cardExit
        ) {
            BankCardCompact(
                lastFour = bankCardPreview.lastFour,
                holderName = bankCardPreview.holderName,
                expiry = bankCardPreview.expiry,
            )
        }
    }

}


@Preview(
    name = "320 × 568 dp — с клавиатурой",
    widthDp = 320,
    heightDp = 568,
    showSystemUi = true,
)
@Composable
private fun CreateBankCardScreenSmallPreview() {
    BankCardScreenPreviewContent(simulateIme = true)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BankCardScreenPreviewContent(simulateIme: Boolean = false) {
    val showSimulatedIme = simulateIme && LocalInspectionMode.current
    val simulatedImeHeight = if (showSimulatedIme) 220.dp else 0.dp

    var bankCardFormState by remember {
        mutableStateOf(
            RecordForm.BankCard(
                cardTitle = "",
                cardNumber = "",
                cardExpiry = "",
                cardCvv = "",
                cardHolder = "",
            )
        )
    }
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
            CreateBankCardScreen(
                isImeVisible = showSimulatedIme || WindowInsets.isImeVisible,
                bankCardState = bankCardFormState,
                bankCardPreview = bankCardFormState.toPreview(),
                modifier = Modifier.padding(
                    top = topPadding,
                    start = 16.dp,
                    bottom = bottomPadding + simulatedImeHeight,
                    end = 16.dp,
                ),
                onInput = { formInput, formValue ->
                    val fieldsState = formInput as VaultFormInputs.BankCard
                    bankCardFormState = when (fieldsState) {
                        VaultFormInputs.BankCard.TITLE -> bankCardFormState.copy(cardTitle = formValue)
                        VaultFormInputs.BankCard.NUMBER -> bankCardFormState.copy(cardNumber = formValue)
                        VaultFormInputs.BankCard.HOLDER -> bankCardFormState.copy(cardHolder = formValue)
                        VaultFormInputs.BankCard.EXPIRY -> bankCardFormState.copy(cardExpiry = formValue)
                        VaultFormInputs.BankCard.CVV -> bankCardFormState.copy(cardCvv = formValue)
                    }
                },
                onSelect = { categoryId ->
                    bankCardFormState = bankCardFormState.copy(selectedCategoryId = categoryId)
                },
                categories = listOf(
                    DropdownOption(1, "PERSONAL", R.string.category_personal),
                    DropdownOption(2, "WORK", R.string.category_work),
                    DropdownOption(3, "FINANCE", R.string.category_finance),
                ),
            )
        }
    }
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
    BankCardScreenPreviewContent()
}