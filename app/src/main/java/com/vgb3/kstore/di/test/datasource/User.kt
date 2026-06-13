package com.vgb3.kstore.di.test.datasource

import android.util.Log

class User(private val id: String) {
    init {
        Log.d("CHECK_USER", id)
    }
}