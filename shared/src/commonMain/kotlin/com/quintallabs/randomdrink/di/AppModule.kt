package com.quintallabs.randomdrink.di

import com.quintallabs.randomdrink.di.datasource.api.apiModule
import com.quintallabs.randomdrink.di.datasource.database.drinkDatabaseModule
import com.quintallabs.randomdrink.di.network.networkModule
import com.quintallabs.randomdrink.di.repositories.drinkMockRepositoryModule
import com.quintallabs.randomdrink.di.usecases.drinkUseCaseModule

fun appModule() = listOf(
  drinkMockRepositoryModule,
  drinkUseCaseModule,
  networkModule,
  apiModule,
  drinkDatabaseModule
)