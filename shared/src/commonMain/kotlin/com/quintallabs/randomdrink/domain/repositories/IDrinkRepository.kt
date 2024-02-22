package com.quintallabs.randomdrink.domain.repositories

import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.GeneralError
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain

interface IDrinkRepository {

  suspend fun getRandom(): ResultDomain<Drink, GeneralError>

  suspend fun search(query: String): ResultDomain<List<Drink>, GeneralError>

  suspend fun saveFavoriteDrink(drink: Drink)

  suspend fun deleteFavoriteDrink(drink: Drink)

  suspend fun getFavorites(): ResultDomain<List<Drink>, GeneralError>

  suspend fun getById(id: String): ResultDomain<Drink, GeneralError>
}