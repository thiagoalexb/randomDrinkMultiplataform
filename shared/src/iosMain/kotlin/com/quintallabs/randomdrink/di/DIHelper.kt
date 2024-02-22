package com.quintallabs.randomdrink.di

import com.quintallabs.randomdrink.database.DriverFactory
import com.quintallabs.randomdrink.domain.usecases.DeleteFavoriteDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.GetDrinkByIdUseCase
import com.quintallabs.randomdrink.domain.usecases.GetFavoritesDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.GetRandomDrinkUseCase
import com.quintallabs.randomdrink.domain.usecases.SaveFavoriteDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.SearchDrinkUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

class UseCasesComponent : KoinComponent {
  private val getRandomDrinkUseCase: GetRandomDrinkUseCase by inject()
  fun getRandomDrinkUseCase(): GetRandomDrinkUseCase = getRandomDrinkUseCase

  private val saveFavoriteDrinksUseCase: SaveFavoriteDrinksUseCase by inject()
  fun getSaveFavoritesDrinksUseCase(): SaveFavoriteDrinksUseCase = saveFavoriteDrinksUseCase

  private val deleteFavoriteDrinksUseCase: DeleteFavoriteDrinksUseCase by inject()
  fun getDeleteFavoriteDrinksUseCase(): DeleteFavoriteDrinksUseCase = deleteFavoriteDrinksUseCase

  private val searchDrinkUseCase: SearchDrinkUseCase by inject()
  fun searchDrinkUseCase(): SearchDrinkUseCase = searchDrinkUseCase

  private val getDrinkByIdUseCase: GetDrinkByIdUseCase by inject()
  fun getDrinkByIdUseCase(): GetDrinkByIdUseCase = getDrinkByIdUseCase

  private val getFavoritesDrinksUseCase: GetFavoritesDrinksUseCase by inject()
  fun getFavoritesDrinksUseCase(): GetFavoritesDrinksUseCase = getFavoritesDrinksUseCase
}

val driverFactoryModule = module {
  factory { DriverFactory() }
}

fun initKoin() {
  startKoin {
    modules(appModule() + driverFactoryModule)
  }
}