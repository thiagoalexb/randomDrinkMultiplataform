package com.quintallabs.randomdrink.di.usecases

import com.quintallabs.randomdrink.domain.usecases.DeleteFavoriteDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.GetDrinkByIdUseCase
import com.quintallabs.randomdrink.domain.usecases.GetFavoritesDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.GetRandomDrinkUseCase
import com.quintallabs.randomdrink.domain.usecases.SaveFavoriteDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.SearchDrinkUseCase
import org.koin.dsl.module

val drinkUseCaseModule = module {

  factory { GetRandomDrinkUseCase(get()) }
  factory { SaveFavoriteDrinksUseCase(get()) }
  factory { DeleteFavoriteDrinksUseCase(get()) }
  factory { GetFavoritesDrinksUseCase(get()) }
  factory { SearchDrinkUseCase(get()) }
  factory { GetDrinkByIdUseCase(get()) }
}