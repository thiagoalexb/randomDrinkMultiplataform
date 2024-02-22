package com.quintallabs.randomdrink.android.presentation.search

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quintallabs.randomdrink.domain.models.commons.ResultDomain
import com.quintallabs.randomdrink.domain.usecases.SearchDrinkUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
  private val searchDrinkUseCase: SearchDrinkUseCase
): ViewModel() {

  private val _state = MutableStateFlow(SearchState())
  val state: StateFlow<SearchState> = _state.asStateFlow()

  fun searchDrink(query: String) {
    viewModelScope.launch {
      _state.value.isLoading = true
      when(val searchDrinkResult = searchDrinkUseCase(query)) {
        is ResultDomain.Error -> {

        }
        is ResultDomain.Success -> {
          _state.update { state ->
            state.copy(
              isLoading = false,
              drinks = searchDrinkResult.data.toMutableStateList()
            )
          }
        }
      }
    }
  }

}