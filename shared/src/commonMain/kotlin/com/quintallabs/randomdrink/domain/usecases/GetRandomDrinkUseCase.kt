package com.quintallabs.randomdrink.domain.usecases

import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.GeneralError
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain
import com.quintallabs.randomdrink.domain.repositories.IDrinkRepository

class GetRandomDrinkUseCase (
  private val drinkRepository: IDrinkRepository
) {

  suspend operator fun invoke(): ResultDomain<Drink, GeneralError> =
    drinkRepository.getRandom()

}