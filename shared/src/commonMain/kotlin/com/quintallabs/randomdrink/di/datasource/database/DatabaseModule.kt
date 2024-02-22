package com.quintallabs.randomdrink.di.datasource.database

import com.quintallabs.randomdrink.database.DrinkDatabase
import com.quintallabs.randomdrink.database.DbHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val drinkDatabaseModule  = module{
  factory { CoroutineScope(Dispatchers.IO) }
  single { DbHelper(get()) }
  single { DrinkDatabase(get(), get()) }
}