package com.vgb3.kstore.di.test.presenter

import com.vgb3.kstore.di.test.datasource.LocalSource
import com.vgb3.kstore.di.test.datasource.RemoteSource

class MainActivityPresenter(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) {
}