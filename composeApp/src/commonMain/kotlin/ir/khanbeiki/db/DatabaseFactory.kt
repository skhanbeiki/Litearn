package ir.khanbeiki.db

import app.cash.sqldelight.db.SqlDriver
import ir.khanbeiki.sqldelight.sample.data.Database

expect class DatabaseFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(factory: DatabaseFactory): Database {
    return Database(factory.createDriver())
}
