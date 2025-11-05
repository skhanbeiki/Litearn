package ir.khanbeiki.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import ir.khanbeiki.sqldelight.sample.data.Database

actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Database.Schema, "database.db")
    }
}