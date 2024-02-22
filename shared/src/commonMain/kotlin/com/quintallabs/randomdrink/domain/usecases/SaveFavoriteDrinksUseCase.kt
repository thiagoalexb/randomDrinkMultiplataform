package com.quintallabs.randomdrink.domain.usecases

import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.repositories.IDrinkRepository

class SaveFavoriteDrinksUseCase(
  private val drinkRepository: IDrinkRepository
) {

  suspend operator fun invoke(drink: Drink) {
    drinkRepository.saveFavoriteDrink(drink)
  }
}