package com.quintallabs.randomdrink.extensions.mappers

import com.quintallabs.randomdrink.data.dto.DrinkDTO
import com.quintallabs.randomdrink.domain.models.Drink
import comquintallabsrandomdrink.FavoriteDrink


fun DrinkDTO.toDrink(): Drink {
  return Drink(
    id = this.idDrink,
    drinkName = this.strDrink,
    drinkAlternative = this.strDrinkAlternate,
    tags = getTags(this.strTags),
    videoUrl = this.strVideo,
    category = this.strCategory,
    iba = this.strIba,
    alcoholic = this.strAlcoholic,
    glassType = this.strGlass,
    instructions = this.strInstructions,
    drinkThumb = this.strDrinkThumb,
    ingredients = getIngredients(this),
    measures = getMeasures(this),
    imageUrl = this.strImageSource,
    imageAttribution = this.strImageAttribution,
    dateModified = this.dateModified,
  )
}

private fun getTags(tags: String?): List<String> {
  if(tags == null) return emptyList()

  return tags.split(",")
}

private fun getIngredients(drink: DrinkDTO): List<String> {
  val ingredients = mutableListOf<String>()
  ingredients.add(drink.strIngredient1 ?: "")
  ingredients.add(drink.strIngredient2 ?: "")
  ingredients.add(drink.strIngredient3 ?: "")
  ingredients.add(drink.strIngredient4 ?: "")
  ingredients.add(drink.strIngredient5 ?: "")
  ingredients.add(drink.strIngredient6 ?: "")
  ingredients.add(drink.strIngredient7 ?: "")
  ingredients.add(drink.strIngredient8 ?: "")
  ingredients.add(drink.strIngredient9 ?: "")
  ingredients.add(drink.strIngredient10 ?: "")
  ingredients.add(drink.strIngredient11 ?: "")
  ingredients.add(drink.strIngredient12 ?: "")
  ingredients.add(drink.strIngredient13 ?: "")
  ingredients.add(drink.strIngredient14 ?: "")
  ingredients.add(drink.strIngredient15 ?: "")

  return ingredients.filter { it.isNotBlank() }
}

private fun getMeasures(drink: DrinkDTO): List<String> {
  val measures = mutableListOf<String>()
  measures.add(drink.strMeasure1 ?: "")
  measures.add(drink.strMeasure2 ?: "")
  measures.add(drink.strMeasure3 ?: "")
  measures.add(drink.strMeasure4 ?: "")
  measures.add(drink.strMeasure5 ?: "")
  measures.add(drink.strMeasure6 ?: "")
  measures.add(drink.strMeasure7 ?: "")
  measures.add(drink.strMeasure8 ?: "")
  measures.add(drink.strMeasure9 ?: "")
  measures.add(drink.strMeasure10 ?: "")
  measures.add(drink.strMeasure11 ?: "")
  measures.add(drink.strMeasure12 ?: "")
  measures.add(drink.strMeasure13 ?: "")
  measures.add(drink.strMeasure14 ?: "")
  measures.add(drink.strMeasure15 ?: "")

  return measures.filter { it.isNotBlank() }
}

fun FavoriteDrink.toDrink(): Drink {
  return Drink(
    id = this.id,
    drinkName = this.drink_name,
    drinkAlternative = this.drink_alternative,
    tags = this.tags ?: listOf(),
    videoUrl = this.video_url,
    category = this.category,
    iba = this.iba,
    alcoholic = this.alcoholic,
    glassType = this.glass_type,
    instructions = this.instructions,
    drinkThumb = this.drink_thumb,
    ingredients = this.ingredients ?: listOf(),
    measures = this.measures ?: listOf(),
    imageUrl = this.image_url,
    imageAttribution = this.image_attribution,
    dateModified = this.date_modified,
  )
}