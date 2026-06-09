package com.vgb3.kstore.di.dagger

import com.vgb3.kstore.di.test.datasource.LocalSource
import com.vgb3.kstore.di.test.datasource.RemoteSource
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun provideLocalSource(): LocalSource {
        return LocalSource()
    }

    @Provides
    fun provideRemoteSource(): RemoteSource {
        return RemoteSource()
    }
}