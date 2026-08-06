package com.vgb3.kstore.domain.interactor

import com.vgb3.kstore.domain.model.VaultItem
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class VaultInteractorImplTest {
    private val vaultInteractor = mockk<VaultInteractor>()
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should return row idx gt -1`() = runTest {
        /*
           val id: String = "0",
           val appName: String,
           val url: String,
           val login: String,
           val password: String,
            */

        launch {
            vaultInteractor.createVaultItem(
                VaultItem(
                    id = "0",
                    appName = "foo",
                    url = "bar.com",
                    login = "vgb3@gmail.com",
                    password = "baz"
                )
            )
        }
    }
}