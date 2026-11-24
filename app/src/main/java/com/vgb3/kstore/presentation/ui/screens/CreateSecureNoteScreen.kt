package com.vgb3.kstore.presentation.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vgb3.kstore.R
import com.vgb3.kstore.presentation.model.RecordForm
import com.vgb3.kstore.presentation.model.VaultFormInputs
import com.vgb3.kstore.presentation.ui.model.DropdownOption
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
fun CreateSecureNoteScreen(
    secureNoteState: RecordForm.SecureNote,
    categories: List<DropdownOption>,
    onInput: (VaultFormInputs, String) -> Unit,
    onSelect: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Form(
        modifier = modifier,
        formContent = { onInput, onSelect ->
            RecordTypePlate(
                style = styleFor("secure_note")
            )
            GroupContent {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    InputField(
                        value = secureNoteState.secureTitle,
                        onValueChange = { onInput(VaultFormInputs.SecureNote.TITLE, it) },
                        titleField = {
                            Text(
                                text = stringResource(R.string.secure_name_title),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.text_icon),
                                contentDescription = "",
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.secure_name_placeholder),
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
                        selected = secureNoteState.selectedCategoryId ?: 0,
                        onSelect = onSelect
                    )
                    InputField(
                        value = secureNoteState.secureText,
                        minLines = 6,
                        maxLines = 12,
                        singleLine = false,
                        onValueChange = { onInput(VaultFormInputs.SecureNote.TEXT, it) },
                        titleField = {
                            Text(
                                text = stringResource(R.string.secure_note_title),
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Default
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.secure_note_placeholder),
                                fontSize = 16.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
        },
        onInput = onInput,
        onSelect = onSelect
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
private fun CreateSecureNoteScreenPreview() {
    var secureFormState by remember {
        mutableStateOf(
            RecordForm.SecureNote(
                secureTitle = "Домашний wifi",
                secureText = "Некоторый текст"
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
                    "Новая заметка",
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
            CreateSecureNoteScreen(
                secureNoteState = secureFormState,
                onInput = { formInput, formValue ->
                    val fieldsState = formInput as VaultFormInputs.SecureNote
                    secureFormState = when (fieldsState) {
                        VaultFormInputs.SecureNote.TEXT -> secureFormState.copy(secureText = formValue)
                        VaultFormInputs.SecureNote.TITLE -> secureFormState.copy(secureTitle = formValue)
                    }
                },
                onSelect = {},
                categories = emptyList(),
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