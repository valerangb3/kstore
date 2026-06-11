package com.vgb3.kstore.di.dagger.modules

import com.vgb3.kstore.di.test.datasource.LocalSource
import com.vgb3.kstore.di.test.datasource.RemoteSource
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideLocalSource(): LocalSource {
        return LocalSource()
    }

    @Provides
    fun provideRemoteSource(): RemoteSource {
        return RemoteSource()
    }
}