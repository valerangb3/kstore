package com.vgb3.kstore

import android.app.Application
import com.vgb3.kstore.di.Container
import com.vgb3.kstore.di.dagger.components.DaggerDataSourceComponent
import com.vgb3.kstore.di.dagger.modules.MainModule

class KStoreApplication: Application() {
    val dataSourceComponent = DaggerDataSourceComponent
        .builder()
        .mainModule(MainModule(this))
        .build()
    lateinit var diContainer: Container
    override fun onCreate() {
        super.onCreate()
        diContainer = Container(this)
    }
}