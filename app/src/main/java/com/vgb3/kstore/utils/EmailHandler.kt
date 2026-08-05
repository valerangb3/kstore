package com.vgb3.kstore.utils

class EmailHandler {
    private val regex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+")

    fun validate(email: String): Boolean {
        return regex.matches(email)
    }
}