package com.quintallabs.randomdrink.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain
import com.quintallabs.randomdrink.domain.usecases.DeleteFavoriteDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.GetFavoritesDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.GetRandomDrinkUseCase
import com.quintallabs.randomdrink.domain.usecases.SaveFavoriteDrinksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
  private val getRandomDrinkUseCase: GetRandomDrinkUseCase,
  private val getFavoritesDrinksUseCase: GetFavoritesDrinksUseCase,
  private val saveFavoriteDrinksUseCase: SaveFavoriteDrinksUseCase,
  private val deleteFavoriteDrinksUseCase: DeleteFavoriteDrinksUseCase,
) : ViewModel() {

  private val _state = MutableStateFlow(HomeState(isLoading = true))
  val state: StateFlow<HomeState> = _state.asStateFlow()

  init {
    getNewRandomDrink()
  }

  fun getNewRandomDrink() {
    viewModelScope.launch {
      _state.update { state ->
        state.copy(
          isLoading = true
        )
      }

      when (val randomDrinkResult = getRandomDrinkUseCase()) {
        is ResultDomain.Error -> {
          _state.update { state ->
            state.copy(
              isLoading = false,
              generalError = randomDrinkResult.error
            )
          }
        }

        is ResultDomain.Success -> {
          val favoritesDrinksResult = getFavoritesDrinksUseCase()
          var favorites = listOf<Drink>()

          if (favoritesDrinksResult is ResultDomain.Success)
            favorites = favoritesDrinksResult.data


          _state.update { state ->
            val isFavorite = favorites.any { it.id == randomDrinkResult.data.id }
            state.copy(
              isLoading = false,
              randomDrink = randomDrinkResult.data,
              isFavorite = isFavorite,
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
}