package com.quintallabs.randomdrink.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class RandomDrinkResponseDTO(
  val drinks: List<DrinkDTO>
)