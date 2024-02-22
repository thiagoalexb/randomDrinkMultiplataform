package com.quintallabs.randomdrink.android.presentation.search.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain
import com.quintallabs.randomdrink.domain.usecases.DeleteFavoriteDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.GetDrinkByIdUseCase
import com.quintallabs.randomdrink.domain.usecases.GetFavoritesDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.SaveFavoriteDrinksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DrinkDetailViewModel(
  private val getDrinkByIdUseCase: GetDrinkByIdUseCase,
  private val getFavoritesDrinksUseCase: GetFavoritesDrinksUseCase,
  private val saveFavoriteDrinksUseCase: SaveFavoriteDrinksUseCase,
  private val deleteFavoriteDrinksUseCase: DeleteFavoriteDrinksUseCase
) : ViewModel() {

  private val _state = MutableStateFlow(DrinkDetailState(isLoading = true))
  val state: StateFlow<DrinkDetailState> = _state.asStateFlow()

  fun getDrink(id: String) {
    viewModelScope.launch {
      _state.update { state ->
        state.copy(
          isLoading = true
        )
      }

      when (val drinkByIdResult = getDrinkByIdUseCase(id)) {
        is ResultDomain.Error -> {
          _state.update { state ->
            state.copy(
              isLoading = false,
              generalError = drinkByIdResult.error
            )
          }
        }

        is ResultDomain.Success -> {
          val favoritesDrinksResult = getFavoritesDrinksUseCase()
          var favorites = listOf<Drink>()

          if(favoritesDrinksResult is ResultDomain.Success)
            favorites = favoritesDrinksResult.data

          _state.update { state ->
            state.copy(
              isLoading = false,
              drink = drinkByIdResult.data,
              isFavorite = favorites.any { it.id == drinkByIdResult.data.id },
              favorites = favorites
            )
          }
        }
      }
    }
  }


  fun saveFavoriteDrink(drink: Drink) {
    viewModelScope.launch {
      if (drink.isFavorite)
        saveFavoriteDrinksUseCase(drink)
      else
        deleteFavoriteDrinksUseCase(drink)
    }
  }

  private fun checkIfIsFavorite(drink: Drink, favorites: MutableList<Drink>): Boolean {
    return favorites.any { it.id == drink.id }
  }
}