package com.quintallabs.randomdrink.android.presentation.search.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.quintallabs.randomdrink.android.ui.components.DrinkCard
import com.quintallabs.randomdrink.android.ui.components.LoadingDialog
import com.quintallabs.randomdrink.android.ui.components.TopBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrinkDetailScreen(
  viewModel: DrinkDetailViewModel = koinViewModel(),
  navController: NavController,
  drinkId: String?,
) {

  val topBarTitle = remember {
    mutableStateOf("")
  }

  Scaffold(
    topBar = {
      TopBar(navController = navController, topBarTitle.value)
    }
  ) { _ ->

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = drinkId) {
      viewModel.getDrink(drinkId!!)
    }

    LoadingDialog(state.isLoading)

    state.drink?.let {
      topBarTitle.value = it.drinkName
      Column(
        modifier = Modifier
          .background(MaterialTheme.colorScheme.background)
          .verticalScroll(rememberScrollState())
          .wrapContentSize(Alignment.TopCenter)
          .padding(16.dp)
      ) {
        DrinkCard(
          drink = it,
          viewModel::saveFavoriteDrink
        )
      }
    }
  }
}