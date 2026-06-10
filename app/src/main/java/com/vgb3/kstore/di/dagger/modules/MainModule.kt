package com.vgb3.kstore.di.dagger.modules

import android.content.Context
import android.content.res.Resources
import com.vgb3.kstore.di.test.datasource.LocalSource
import com.vgb3.kstore.di.test.datasource.RemoteSource
import com.vgb3.kstore.di.test.presenter.MainActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val context: Context) {

    @Provides
    fun provideMainActivityProvider(localSource: LocalSource, remoteSource: RemoteSource): MainActivityPresenter {
        return MainActivityPresenter(localSource, remoteSource)
    }

    @Provides
    fun getResources(): Resources {
        return context.resources
    }
}