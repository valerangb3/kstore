package com.vgb3.kstore.di.dagger

import com.vgb3.kstore.MainActivity
import dagger.Component

@Component(modules = [DataSourceModule::class])
interface DataSourceComponent {
    fun inject(activity: MainActivity)
}