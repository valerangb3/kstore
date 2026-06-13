package com.vgb3.kstore.di.dagger.modules

import com.vgb3.kstore.di.test.datasource.User
import dagger.Module
import dagger.Provides

@Module
class UserModule {
    @Provides
    fun provideUser(id: String): User {
        return User(id)
    }
}