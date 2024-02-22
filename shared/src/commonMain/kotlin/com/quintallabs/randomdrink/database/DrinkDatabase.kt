package com.quintallabs.randomdrink.database

import app.cash.sqldelight.async.coroutines.awaitAsList
import com.quintallabs.randomdrink.domain.models.Drink
import comquintallabsrandomdrink.FavoriteDrink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DrinkDatabase(
  private val dbHelper: DbHelper,
  private val scope: CoroutineScope
) {

  suspend fun getAllFavoriteDrinks(): List<FavoriteDrink> {
    var favorites: List<FavoriteDrink>
    val result = scope.async {
      dbHelper.withDatabase {
        favorites = it.appDatabaseQueries.getAllFavoriteDrinks(::mapFavoriteDrink).awaitAsList()
        favorites
      }
    }
    return result.await()
  }

  private fun mapFavoriteDrink(
    id: String,
    drink_name: String,
    drink_alternative: String?,
    tags: List<String>?,
    video_url: String?,
    category: String?,
    iba: String?,
    alcoholic: String?,
    glass_type: String?,
    instructions: String?,
    drink_thumb: String?,
    ingredients: List<String>?,
    measures: List<String>?,
    image_url: String?,
    image_attribution: String?,
    date_modified: String?,
  ): FavoriteDrink {
    return FavoriteDrink(
      id,
      drink_name,
      drink_alternative,
      tags,
      video_url,
      category,
      iba,
      alcoholic,
      glass_type,
      instructions,
      drink_thumb,
      ingredients,
      measures,
      image_url,
      image_attribution,
      date_modified,
    )
  }

  suspend fun saveFavoriteDrink(drink: Drink) {
    scope.launch {
      dbHelper.withDatabase {
        it.appDatabaseQueries.insertFavoriteDrink(
          drink.id,
          drink.drinkName,
          drink.drinkAlternative,
          drink.tags,
          drink.videoUrl,
          drink.category,
          drink.iba,
          drink.alcoholic,
          drink.glassType,
          drink.instructions,
          drink.drinkThumb,
          drink.ingredients,
          drink.measures,
          drink.imageUrl,
          drink.imageAttribution,
          drink.dateModified,
        )
      }
    }
  }

  suspend fun deleteFavoriteDrink(drink: Drink) {
    scope.launch {
      dbHelper.withDatabase {
        it.appDatabaseQueries.deleteFavoriteDrink(
          drink.id
        )
      }
    }
  }
}