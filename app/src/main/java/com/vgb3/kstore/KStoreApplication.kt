package com.vgb3.kstore

import android.app.Application
import com.vgb3.kstore.di.Container

class KStoreApplication: Application() {
    lateinit var diContainer: Container
    override fun onCreate() {
        super.onCreate()
        diContainer = Container(this)
    }
}