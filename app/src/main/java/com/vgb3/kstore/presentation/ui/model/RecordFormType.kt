package com.vgb3.kstore.presentation.ui.model

import kotlinx.serialization.Serializable

@Serializable
enum class RecordFormType(val key: String) {
    LOGIN("login"),
    BANK_CARD("bank_card"),
    SECURE_NOTE("secure_note");

    companion object{
        fun fromKey(key: String) = entries.find { it.key == key }
    }
}