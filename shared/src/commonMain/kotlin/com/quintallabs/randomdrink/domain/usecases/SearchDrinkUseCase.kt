package com.quintallabs.randomdrink.domain.usecases

import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.GeneralError
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain
import com.quintallabs.randomdrink.domain.repositories.IDrinkRepository

class SearchDrinkUseCase (
  private val drinkRepository: IDrinkRepository,
) {

  suspend operator fun invoke(query: String): ResultDomain<List<Drink>, GeneralError> =
    drinkRepository.search(query)

}