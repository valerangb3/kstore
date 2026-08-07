package com.vgb3.kstore.data.repository

import com.vgb3.kstore.data.datasource.local.db.dao.VaultDao
import com.vgb3.kstore.data.datasource.local.db.entities.VaultEntity
import com.vgb3.kstore.domain.model.VaultItem
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
class VaultRepositoryImplTest {
    private val vaultDao = mockk<VaultDao>()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: VaultRepositoryImpl

    @Before
    fun setUp() {
        repository = VaultRepositoryImpl(
            vaultDao = vaultDao,
            coroutineDispatcher = testDispatcher
        )
    }

    @Test
    fun `should map vault item and return inserted row id`() = runTest(context = testDispatcher) {
        val vaultItem = VaultItem(
            id = "0",
            appName = "ya.ru",
            url = "ya.ru",
            login = "foo",
            password = "bar",
        )

        val expectedEntity = VaultEntity(
            id = 0L,
            appName = "ya.ru",
            picUrl = "ya.ru",
            login = "foo",
            password = "bar"
        )

        coEvery {
            vaultDao.insertNewVault(expectedEntity)
        } returns 1L

        val result = repository.createVaultItem(vaultItem)
        Assert.assertEquals(1L, result)

        coVerify(exactly = 1) {
            vaultDao.insertNewVault(expectedEntity)
        }
    }

    @Test
    fun `should map vault entities to vault items`() = runTest(context = testDispatcher) {
        val entities = listOf(
            VaultEntity(
                id = 1L,
                appName = "ya.ru",
                picUrl = "ya.ru",
                login = "foo",
                password = "123"
            ),
            VaultEntity(
                id = 2L,
                appName = "google.ru",
                picUrl = "google.ru",
                login = "bar",
                password = "321"
            )
        )

        val expected = listOf(
            VaultItem(
                id = "1",
                appName = "ya.ru",
                url = "ya.ru",
                login = "foo",
                password = "123",
            ),
            VaultItem(
                id = "2",
                appName = "google.ru",
                url = "google.ru",
                login = "bar",
                password = "321",
            )
        )

        every {
            vaultDao.getVaultItems()
        } returns flowOf(entities)

        val result = repository.getVaultItems().first()

        Assert.assertEquals(expected, result)
    }
}