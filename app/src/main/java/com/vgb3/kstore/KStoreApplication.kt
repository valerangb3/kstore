package com.vgb3.kstore

import android.app.Application
import com.vgb3.kstore.di.Container
import com.vgb3.kstore.di.dagger.components.DaggerAppComponent
import com.vgb3.kstore.di.dagger.modules.MainModule

class KStoreApplication: Application() {
    val appComponent = DaggerAppComponent
        .builder()
        .context(this)
        .build()
    lateinit var diContainer: Container
    override fun onCreate() {
        super.onCreate()
        diContainer = Container(this)
    }
}