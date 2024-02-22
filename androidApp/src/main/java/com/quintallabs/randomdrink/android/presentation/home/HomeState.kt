package com.quintallabs.randomdrink.android.presentation.home

import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.GeneralError

data class HomeState(
  val isLoading: Boolean = false,
  val randomDrink: Drink? = null,
  val isFavorite: Boolean = false,
  val generalError: GeneralError? = null,
  val favorites: List<Drink> = mutableListOf()
)