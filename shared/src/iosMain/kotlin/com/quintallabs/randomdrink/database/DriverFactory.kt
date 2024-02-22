package com.quintallabs.randomdrink.database

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.quintallabs.randomdrink.AppDatabase

actual class DriverFactory {
  actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(AppDatabase.Schema.synchronous(), "app.db")
  }
}