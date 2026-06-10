package com.vgb3.kstore.di.dagger.components

import android.content.Context
import com.vgb3.kstore.MainActivity
import com.vgb3.kstore.di.dagger.modules.DataSourceModule
import com.vgb3.kstore.di.dagger.modules.MainModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataSourceModule::class, MainModule::class])
interface DataSourceComponent {
    fun inject(activity: MainActivity)

    @Component.Builder
    interface DataSourceComponentBuilder {
        fun buildDataSourceComponent(): DataSourceComponent
        @BindsInstance
        fun context(context: Context): DataSourceComponentBuilder
    }
}