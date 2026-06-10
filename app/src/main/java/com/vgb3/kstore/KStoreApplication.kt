package com.vgb3.kstore

import android.app.Application
import com.vgb3.kstore.di.Container
import com.vgb3.kstore.di.dagger.components.DaggerDataSourceComponent
import com.vgb3.kstore.di.dagger.modules.MainModule

class KStoreApplication: Application() {
    val dataSourceComponent = DaggerDataSourceComponent
        .builder()
        .context(this)
        .buildDataSourceComponent()
    lateinit var diContainer: Container
    override fun onCreate() {
        super.onCreate()
        diContainer = Container(this)
    }
}