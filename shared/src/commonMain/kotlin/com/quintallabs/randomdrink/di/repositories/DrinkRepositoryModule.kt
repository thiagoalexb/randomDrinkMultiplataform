package com.quintallabs.randomdrink.di.repositories

import com.quintallabs.randomdrink.domain.repositories.IDrinkRepository
import com.quintallabs.randomdrink.repository.drink.DrinkRepository
import org.koin.dsl.module

val drinkMockRepositoryModule = module {
  factory<IDrinkRepository> { DrinkRepository(get(), get(), get()) }
}