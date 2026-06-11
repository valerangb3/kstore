package com.vgb3.kstore.di.dagger.components

import com.vgb3.kstore.di.dagger.modules.MainModule
import com.vgb3.kstore.di.test.presenter.MainActivityPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun getMainPresenter(): MainActivityPresenter
}