package com.quintallabs.randomdrink.android.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quintallabs.randomdrink.android.ui.components.DrinkCard
import com.quintallabs.randomdrink.android.ui.components.LoadingDialog
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
  viewModel:HomeViewModel = koinViewModel()
) {

  Scaffold(
    floatingActionButton = {
      FloatingActionButton(
        modifier = Modifier
          .wrapContentSize(Alignment.BottomEnd),
        onClick = {
          viewModel.getNewRandomDrink()
        },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondary
      ) {
        Icon(Icons.Filled.Refresh, "Refresh Drink")
      }
    }
  ) { _ ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
      val state = viewModel.state.collectAsState()

      LoadingDialog(state.value.isLoading)

      state.value.randomDrink?.let {
        Column(
          modifier = Modifier
            .verticalScroll(rememberScrollState())
            .wrapContentSize(Alignment.TopCenter)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
          DrinkCard(
            drink = it,
            viewModel::saveFavoriteDrink
          )
        }
      }
    }
  }
}