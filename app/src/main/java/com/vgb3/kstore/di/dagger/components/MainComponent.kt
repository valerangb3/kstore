package com.vgb3.kstore.di.dagger.components

import com.vgb3.kstore.di.dagger.modules.MainModule
import com.vgb3.kstore.di.dagger.modules.UserModule
import com.vgb3.kstore.di.test.datasource.User
import com.vgb3.kstore.di.test.presenter.MainActivityPresenter
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class, UserModule::class])
interface MainComponent {
    fun getMainPresenter(): MainActivityPresenter
    fun getUser(): User

    @Subcomponent.Builder
    interface MainComponentBuilder {
        @BindsInstance
        fun setId(id: String): MainComponentBuilder
        fun build(): MainComponent
    }
}