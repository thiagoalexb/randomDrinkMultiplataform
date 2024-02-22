package com.quintallabs.randomdrink.android.presentation.search.detail

import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.GeneralError

data class DrinkDetailState(
  val isLoading: Boolean = false,
  val drink: Drink? = null,
  val generalError: GeneralError? = null,
  val favorites: List<Drink> = mutableListOf(),
  val isFavorite: Boolean = false
)