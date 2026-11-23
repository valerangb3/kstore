package com.vgb3.kstore.presentation.model

sealed interface VaultFormInputs {
    enum class Login : VaultFormInputs {
        APP_NAME_INPUT, URL_INPUT, LOGIN_INPUT, PASSWORD_INPUT;
    }
    enum class BankCard : VaultFormInputs {
        TITLE, NUMBER, EXPIRY, CVV, HOLDER;
    }
    enum class SecureNote : VaultFormInputs {
        TITLE, TEXT
    }
}