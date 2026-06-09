package com.vgb3.kstore

import android.app.Application
import com.vgb3.kstore.di.Container
import com.vgb3.kstore.di.dagger.DaggerDataSourceComponent

class KStoreApplication: Application() {
    val dataSourceComponent = DaggerDataSourceComponent.create()
    lateinit var diContainer: Container
    override fun onCreate() {
        super.onCreate()
        diContainer = Container(this)
    }
}