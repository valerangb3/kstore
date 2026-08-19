package com.vgb3.kstore.domain.interactor

import com.vgb3.kstore.domain.model.output.VaultItem
import com.vgb3.kstore.domain.repository.VaultRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class VaultInteractorImplTest {

    private val repository = mockk<VaultRepository>()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var interactor: VaultInteractorImpl

    @Before
    fun setUp() {
        interactor = VaultInteractorImpl(
            vaultRepository = repository,
            coroutineDispatcher = testDispatcher
        )
    }

    @Test
    fun `should return vault id after create new row`() = runTest(context = testDispatcher) {
        val vaultItem = VaultItem(
            id = "0",
            appName = "ya.ru",
            url = "ya.ru",
            login = "foo",
            password = "bar",
        )
        coEvery {
           repository.createVaultItem(vaultItem)
        } returns 1L

        val result = interactor.createVaultItem(
            vaultItem
        )

        Assert.assertEquals( 1L, result)

        coVerify(exactly = 1) {
            repository.createVaultItem(vaultItem)
        }
    }

    @Test
    fun `should return flow VaultItem list`() = runTest(context = testDispatcher) {
        val items = listOf(
            VaultItem(
                id = "1",
                appName = "ya.ru",
                url = "ya.ru",
                login = "foo",
                password = "bar",
            ),
            VaultItem(
                id = "2",
                appName = "google.com",
                url = "google.com",
                login = "baz",
                password = "bar",
            ),
            VaultItem(
                id = "3",
                appName = "rambler.ru",
                url = "rambler.ru",
                login = "bar",
                password = "foo",
            )
        )
        val flow = flowOf(items)

        every {
            repository.getVaultItems()
        } returns flow

        val result = interactor.getVaultItems().first()
        Assert.assertEquals(items, result)
    }
}