package com.quintallabs.randomdrink.domain.usecases

import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.GeneralError
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain
import com.quintallabs.randomdrink.domain.repositories.IDrinkRepository

class GetFavoritesDrinksUseCase(
  private val drinkRepository: IDrinkRepository,
) {

  suspend operator fun invoke(): ResultDomain<List<Drink>, GeneralError>  {
    val favoritesResult = drinkRepository.getFavorites()
    return when(favoritesResult) {
      is ResultDomain.Error -> {
        favoritesResult
      }
      is ResultDomain.Success -> {
        favoritesResult.data.forEach {
          it.isFavorite = true
        }
        favoritesResult
      }
    }
  }


}