package com.quintallabs.randomdrink.domain.models

data class Drink(
  val id: String,
  val drinkName: String,
  val drinkAlternative: String?,
  val tags: List<String>,
  val videoUrl: String?,
  val category: String?,
  val iba: String?,
  val alcoholic: String?,
  val glassType: String?,
  val instructions: String?,
  val drinkThumb: String?,
  val ingredients: List<String>,
  val measures: List<String>,
  val imageUrl: String?,
  val imageAttribution: String?,
  val dateModified: String?,
  var isFavorite: Boolean = false
)