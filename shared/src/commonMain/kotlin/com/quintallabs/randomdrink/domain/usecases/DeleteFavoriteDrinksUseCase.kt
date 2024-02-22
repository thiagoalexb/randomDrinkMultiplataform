package com.quintallabs.randomdrink.domain.usecases

import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.repositories.IDrinkRepository

class DeleteFavoriteDrinksUseCase(
  private val drinkRepository: IDrinkRepository
) {

  suspend operator fun invoke(drink: Drink) {
    drinkRepository.deleteFavoriteDrink(drink)
  }
}