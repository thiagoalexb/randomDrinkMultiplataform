package com.quintallabs.randomdrink.android.presentation.search

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.quintallabs.randomdrink.domain.models.Drink

data class SearchState(
  var isLoading: Boolean = false,
  val drinks: SnapshotStateList<Drink> = mutableStateListOf(),
)