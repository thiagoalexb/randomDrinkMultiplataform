package com.quintallabs.randomdrink.android.presentation.favorites

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.quintallabs.randomdrink.domain.models.Drink

data class FavoritesState(
  var isLoading: Boolean = false,
  var favorites: SnapshotStateList<Drink> = mutableStateListOf()
)