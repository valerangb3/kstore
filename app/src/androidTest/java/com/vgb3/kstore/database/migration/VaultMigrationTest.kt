package com.vgb3.kstore.database.migration

import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vgb3.kstore.data.datasource.local.db.KStoreDataBase
import com.vgb3.kstore.data.datasource.local.db.migrations.MIGRATION_1_2
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VaultMigrationTest {

    private val dbName = "vault-migration-test"

    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        KStoreDataBase::class.java
    )

    @Test
    fun migration1To2_whenDatabaseMigrated_preservesVaultData() {
        var db = helper.createDatabase(
            dbName,
            1
        )

        db.execSQL(
            """
    INSERT INTO vault_table
        (id, appName, picUrl, login, password)
    VALUES
        (1, 'GitHub', 'icon.png', 'valera', '12345')
    """.trimIndent()
        )

        db.close()

        db = helper.runMigrationsAndValidate(
            dbName,
            2,
            true,
            MIGRATION_1_2
        )

        val cursor = db.query(
            "SELECT title, icon FROM vault_table WHERE id = 21"
        )

        assertTrue(cursor.moveToFirst())

        assertEquals(
            "ssdasd",
            cursor.getString(cursor.getColumnIndexOrThrow("title"))
        )

        assertEquals(
            "sadasdas",
            cursor.getString(cursor.getColumnIndexOrThrow("icon"))
        )

        cursor.close()
        db.close()
    }
}