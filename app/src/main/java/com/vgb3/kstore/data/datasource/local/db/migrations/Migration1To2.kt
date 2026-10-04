package com.vgb3.kstore.data.datasource.local.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS `record_types` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`code` TEXT NOT NULL, " +
                    "`sortOrder` INTEGER NOT NULL)"
        )
        db.execSQL(
            "CREATE UNIQUE INDEX IF NOT EXISTS `index_record_types_code` " +
                    "ON `record_types` (`code`)"
        )
        seedRecordTypes(db)
    }
}

fun seedRecordTypes(db: SupportSQLiteDatabase) {
    db.execSQL(
        "INSERT OR IGNORE INTO `record_types` (`code`, `sortOrder`) VALUES " +
                "('login', 5), ('bank_card', 10), ('secure_note', 15)"
    )
}