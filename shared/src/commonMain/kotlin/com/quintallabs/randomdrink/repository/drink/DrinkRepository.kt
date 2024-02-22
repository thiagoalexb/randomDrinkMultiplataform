package com.quintallabs.randomdrink.repository.drink

import com.quintallabs.randomdrink.database.DrinkDatabase
import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.GeneralError
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain
import com.quintallabs.randomdrink.domain.repositories.IDrinkRepository
import com.quintallabs.randomdrink.extensions.mappers.toDrink
import com.quintallabs.randomdrink.repository.datasource.api.DrinkApi
import com.quintallabs.randomdrink.repository.datasource.base.ApiResultWrapper
import com.quintallabs.randomdrink.repository.datasource.base.ApiUtil

class DrinkRepository(
  private val apiUtil: ApiUtil,
  private val drinkApi: DrinkApi,
  private val drinkDatabase: DrinkDatabase,
) : IDrinkRepository {
  override suspend fun getRandom(): ResultDomain<Drink, GeneralError> {
    val call = apiUtil.safeApiCall {
      drinkApi.getRandomDrink()
    }
    return when (call) {
      is ApiResultWrapper.Error -> {
        ResultDomain.Error(call.error)
      }

      is ApiResultWrapper.Success -> {
        val drinks = call.value.drinks

        ResultDomain.Success(drinks.first().toDrink())
      }
    }
  }

  override suspend fun search(query: String): ResultDomain<List<Drink>, GeneralError> {
    val call = apiUtil.safeApiCall {
      drinkApi.searchDrink(query)
    }

    return when (call) {
      is ApiResultWrapper.Error -> {
        ResultDomain.Error(call.error)
      }

      is ApiResultWrapper.Success -> {
        val drinks = call.value.drinks

        val drinksResult = mutableListOf<Drink>()

        for (drink in drinks) {
          drinksResult.add(drink.toDrink())
        }

        ResultDomain.Success(drinksResult.toList())
      }
    }
  }

  override suspend fun saveFavoriteDrink(drink: Drink) {
    drinkDatabase.saveFavoriteDrink(drink)
  }

  override suspend fun deleteFavoriteDrink(drink: Drink) {
    drinkDatabase.deleteFavoriteDrink(drink)
  }

  override suspend fun getFavorites(): ResultDomain<List<Drink>, GeneralError> {
    return try {
      val allFavoriteDrinks = drinkDatabase.getAllFavoriteDrinks()
      val favorites = mutableListOf<Drink>()

      for (favorite in allFavoriteDrinks) {
        favorites.add(favorite.toDrink())
      }

      ResultDomain.Success(favorites)
    } catch (throwable: Throwable) {
      ResultDomain.Error(
        GeneralError(
          title = throwable.message ?: "Db Error",
          detail = throwable.message ?: "Db Error",
          code = 0
        )
      )
    }
  }

  override suspend fun getById(id: String): ResultDomain<Drink, GeneralError> {
    val call = apiUtil.safeApiCall {
      drinkApi.getById(id)
    }

    return when (call) {
      is ApiResultWrapper.Error -> {

        ResultDomain.Error(call.error)
      }

      is ApiResultWrapper.Success -> {
        val drinks = call.value.drinks

        ResultDomain.Success(drinks.first().toDrink())
      }
    }
  }
}