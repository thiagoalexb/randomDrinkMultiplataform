package com.quintallabs.randomdrink.database

import com.quintallabs.randomdrink.AppDatabase
import comquintallabsrandomdrink.FavoriteDrink
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DbHelper(
  private val driverFactory: DriverFactory
) {

  private var db: AppDatabase? = null

  private val mutex = Mutex()

  suspend fun <Result: Any> withDatabase(
    block: suspend (AppDatabase) -> Result
  ): Result {
    return mutex.withLock {
      if(db == null)
        db = createDb(driverFactory)

      return@withLock block(db!!)
    }
  }

  private suspend fun createDb(driverFactory: DriverFactory): AppDatabase {
    return AppDatabase(
      driver = driverFactory.createDriver(),
      FavoriteDrinkAdapter = FavoriteDrink.Adapter(
        tagsAdapter = listOfStringsAdapter,
        ingredientsAdapter = listOfStringsAdapter,
        measuresAdapter = listOfStringsAdapter
      )
    )
  }
}