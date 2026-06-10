package com.vgb3.kstore.di.dagger.components

import com.vgb3.kstore.MainActivity
import com.vgb3.kstore.di.dagger.modules.DataSourceModule
import com.vgb3.kstore.di.dagger.modules.MainModule
import dagger.Component

@Component(modules = [DataSourceModule::class, MainModule::class])
interface DataSourceComponent {
    fun inject(activity: MainActivity)
}