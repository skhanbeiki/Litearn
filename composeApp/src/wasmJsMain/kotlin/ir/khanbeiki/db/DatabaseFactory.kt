package ir.khanbeiki.db

import app.cash.sqldelight.driver.worker.WebWorkerDriver
import app.cash.sqldelight.db.SqlDriver
import org.w3c.dom.Worker

actual class DatabaseFactory {
    actual fun createDriver(): SqlDriver {
        val worker = Worker("sqldelight.worker.js")
        return WebWorkerDriver(worker)
    }
}