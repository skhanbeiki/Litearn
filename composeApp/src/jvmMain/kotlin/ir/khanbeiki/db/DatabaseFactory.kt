package ir.khanbeiki.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import ir.khanbeiki.sqldelight.sample.data.Database
import java.util.Properties

actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:test.db", Properties(), Database.Schema)
        return driver
    }
}