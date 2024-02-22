package com.quintallabs.randomdrink.domain.usecases

import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.GeneralError
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain
import com.quintallabs.randomdrink.domain.repositories.IDrinkRepository

class GetDrinkByIdUseCase(
  private val drinkRepository: IDrinkRepository
) {

  suspend operator fun invoke(id: String): ResultDomain<Drink, GeneralError> =
    drinkRepository.getById(id)

}