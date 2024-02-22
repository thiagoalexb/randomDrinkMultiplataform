package com.quintallabs.randomdrink.android.presentation.favorites

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quintallabs.randomdrink.domain.models.Drink
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain
import com.quintallabs.randomdrink.domain.usecases.DeleteFavoriteDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.GetFavoritesDrinksUseCase
import com.quintallabs.randomdrink.domain.usecases.SaveFavoriteDrinksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
  private val getFavoritesDrinksUseCase: GetFavoritesDrinksUseCase,
  private val deleteFavoriteDrinksUseCase: DeleteFavoriteDrinksUseCase
) : ViewModel() {

  private val _state = MutableStateFlow(FavoritesState(isLoading = true))
  val state: StateFlow<FavoritesState> = _state.asStateFlow()

  init {
    getFavorites()
  }

  private fun getFavorites() {
    viewModelScope.launch {
      val favoritesDrinksResult = getFavoritesDrinksUseCase()

      if (favoritesDrinksResult is ResultDomain.Success) {
        _state.update {
          it.copy(
            favorites = favoritesDrinksResult.data.toMutableStateList()
          )
        }
      }

      _state.update {
        it.copy(
          isLoading = false
        )
      }
    }
  }

  fun saveFavoriteDrink(drink: Drink) {
    viewModelScope.launch {
      val favoriteState = _state.value
      val favorites = favoriteState.favorites

      favorites.removeIf { it.id == drink.id }

      deleteFavoriteDrinksUseCase(drink)

      _state.update {
        it.copy(
          isLoading = false,
          favorites = favorites
        )
      }
    }
  }
}