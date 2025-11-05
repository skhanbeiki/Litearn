package ir.khanbeiki.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import ir.khanbeiki.sqldelight.sample.data.Database

actual class DatabaseFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "database.db")
    }
}