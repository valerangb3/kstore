package com.vgb3.kstore.di.dagger.modules

import com.vgb3.kstore.di.test.datasource.LocalSource
import com.vgb3.kstore.di.test.datasource.RemoteSource
import com.vgb3.kstore.di.test.presenter.MainActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideMainActivityPresenterProvider(localSource: LocalSource, remoteSource: RemoteSource): MainActivityPresenter {
        return MainActivityPresenter(localSource, remoteSource)
    }

}