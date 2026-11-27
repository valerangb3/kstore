package com.vgb3.kstore.database.migration

import androidx.room.testing.MigrationTestHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.vgb3.kstore.data.datasource.local.db.KStoreDataBase
import com.vgb3.kstore.data.datasource.local.db.migrations.MIGRATION_1_2
import com.vgb3.kstore.data.datasource.local.db.migrations.MIGRATION_2_3
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
    fun migration2To3_whenDatabaseMigrated_preservesVaultData() {
        val testDbName = "vault-migration-2-3"
        helper.createDatabase(
            testDbName,
            2
        ).use { db ->
            db.execSQL(
                """
                INSERT INTO vault_table (id, title, createdAt, updatedAt, isFavorite, type)
                VALUES (42, 'Test card', 1000, 1000, 0, 'BANK_CARD');
            """.trimIndent()
            )
            db.execSQL(
                """
                INSERT INTO vault_bank_card_table
                (vaultItemId, cardNumber, cardholderName, expirationMonth, expirationYear, cvv)
                VALUES (42, '0000000000000042', 'TEST USER', 8, 29, '007');
            """.trimIndent()
            )
        }


        val db = helper.runMigrationsAndValidate(testDbName, 3, true, MIGRATION_2_3)

        val cursor = db.query(
            """
                SELECT
                    v.id,
                    v.title,
                    c.vaultItemId,
                    c.cardNumber,
                    c.cardholderName,
                    c.expirationMonth,
                    c.expirationYear,
                    c.cvv
                FROM vault_table AS v
                JOIN vault_bank_card_table AS c ON c.vaultItemId = v.id
                WHERE v.id = 42
            """.trimIndent()
        )
        assertTrue(cursor.moveToFirst())
        assertEquals(
            "007",
            cursor.getString(cursor.getColumnIndexOrThrow("cvv"))
        )
        assertEquals(
            "Test card",
            cursor.getString(cursor.getColumnIndexOrThrow("title"))
        )
        assertEquals(
            "0000000000000042",
            cursor.getString(cursor.getColumnIndexOrThrow("cardNumber"))
        )
        assertEquals(
            "TEST USER",
            cursor.getString(cursor.getColumnIndexOrThrow("cardholderName"))
        )
        assertEquals(
            8,
            cursor.getInt(cursor.getColumnIndexOrThrow("expirationMonth"))
        )
        assertEquals(
            29,
            cursor.getInt(cursor.getColumnIndexOrThrow("expirationYear"))
        )

        cursor.close()
        db.close()
    }

    //todo: fix test
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