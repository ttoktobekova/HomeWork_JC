package com.example.homework_jc.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


object DatabaseMigrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS favorite_characters_new (
                    id INTEGER PRIMARY KEY NOT NULL,
                    name TEXT NOT NULL DEFAULT 'undefined',
                    species TEXT NOT NULL DEFAULT 'undefined',
                    image TEXT NOT NULL DEFAULT 'undefined',
                    isFavorite INTEGER NOT NULL DEFAULT 0
                )
            """)

            db.execSQL("""
                INSERT INTO favorite_characters_new (id, name, species, image, isFavorite)
                SELECT id, name, species, image, isFavorite 
                FROM favorite_characters
            """)

            db.execSQL("DROP TABLE favorite_characters")

            db.execSQL("ALTER TABLE favorite_characters_new RENAME TO favorite_characters")
        }
    }
}