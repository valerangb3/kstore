package com.vgb3.kstore.data.datasource.local.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE vault_table RENAME COLUMN appName TO title"
        )

        db.execSQL(
            "ALTER TABLE vault_table RENAME COLUMN picUrl to icon"
        )
    }
}