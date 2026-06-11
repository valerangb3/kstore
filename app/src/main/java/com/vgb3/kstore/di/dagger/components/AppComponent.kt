package com.vgb3.kstore.di.dagger.components

import android.content.Context
import com.vgb3.kstore.MainActivity
import com.vgb3.kstore.di.dagger.modules.AppModule
import com.vgb3.kstore.di.dagger.modules.MainModule
import com.vgb3.kstore.di.test.datasource.LocalSource
import com.vgb3.kstore.di.test.datasource.RemoteSource
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    //fun inject(activity: MainActivity)
    fun getLocalSource(): LocalSource
    fun getRemoteSource(): RemoteSource
    fun getMainComponent(): MainComponent

    @Component.Builder
    interface AppComponentBuilder {
        @BindsInstance
        fun context(context: Context): AppComponentBuilder
        fun build(): AppComponent
    }
}