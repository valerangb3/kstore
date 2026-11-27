package com.vgb3.kstore.data.datasource.local.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS `vault_bank_card_table_new` (" +
                    "`vaultItemId` INTEGER NOT NULL, " +
                    "`cardNumber` TEXT NOT NULL, " +
                    "`cardholderName` TEXT NOT NULL, " +
                    "`expirationMonth` INTEGER NOT NULL, " +
                    "`expirationYear` INTEGER NOT NULL, " +
                    "`cvv` TEXT NOT NULL, " +
                    "PRIMARY KEY(`vaultItemId`)," +
                    "FOREIGN KEY(`vaultItemId`) REFERENCES `vault_table`(`id`) ON DELETE CASCADE" +
                    ");"
        )
        db.execSQL(
            "INSERT INTO vault_bank_card_table_new (" +
                    "`vaultItemId`, " +
                    "`cardNumber`, " +
                    "`cardholderName`, " +
                    "`expirationMonth`, " +
                    "`expirationYear`, " +
                    "`cvv`) " +
                    "SELECT " +
                    "`vaultItemId`, " +
                    "`cardNumber`, " +
                    "`cardholderName`, " +
                    "`expirationMonth`, " +
                    "`expirationYear`, " +
                    "`cvv` " +
                    " FROM vault_bank_card_table"
        )

        db.execSQL("DROP TABLE `vault_bank_card_table`")
        db.execSQL("ALTER TABLE `vault_bank_card_table_new` RENAME TO `vault_bank_card_table`")
    }
}