package com.quintallabs.randomdrink.android.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
  viewModel: SearchViewModel = koinViewModel(),
  navController: NavController,
) {

  Scaffold { _ ->

    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
      val state by viewModel.state.collectAsState()

      OutlinedTextFieldDrink(viewModel::searchDrink)

      Spacer(modifier = Modifier.height(16.dp))

      LazyColumn(
        modifier = Modifier
          .fillMaxSize()
      ) {

        items(state.drinks) {
          ElevatedCard(
            elevation = CardDefaults.cardElevation(
              defaultElevation = 6.dp
            ),
            modifier = Modifier
              .fillMaxWidth(),
            onClick = {
              navController.navigate("drink_detail_screen/${it.id}")
            },
            colors = CardDefaults.cardColors(
              containerColor = MaterialTheme.colorScheme.primary
            )
          ) {
            Text(
              text = it.drinkName,
              style = MaterialTheme.typography.bodyLarge,
              color = MaterialTheme.colorScheme.onPrimary,
              modifier = Modifier
                .padding(16.dp)
            )
          }
          Spacer(modifier = Modifier.height(16.dp))
        }
      }
    }
  }
}

@Composable
fun OutlinedTextFieldDrink(
  onSearchClick: (String) -> Unit
) {
  val searchTextState = remember { mutableStateOf("") }

  OutlinedTextField(
    value = searchTextState.value,
    onValueChange = { searchTextState.value = it },
    label = { Text("Search for drinks") },
    placeholder = { Text(text = "Ingredients...") },
    trailingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search icon", tint = MaterialTheme.colorScheme.onPrimaryContainer) },
    modifier = Modifier.fillMaxWidth(),
    keyboardOptions = KeyboardOptions(
      imeAction = ImeAction.Search
    ),
    maxLines = 1,
    colors = TextFieldDefaults.outlinedTextFieldColors(
      focusedBorderColor = MaterialTheme.colorScheme.onBackground,
      focusedLabelColor = MaterialTheme.colorScheme.onBackground,
      unfocusedBorderColor = MaterialTheme.colorScheme.onBackground,
      unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
      textColor = MaterialTheme.colorScheme.onBackground,
      cursorColor = MaterialTheme.colorScheme.onBackground,
      placeholderColor = MaterialTheme.colorScheme.onBackground
    ),
    keyboardActions = KeyboardActions(
      onSearch = {
        onSearchClick(searchTextState.value)
      }
    )
  )
}

@Preview
@Composable
fun preview() {
  Scaffold { _ ->
    OutlinedTextFieldDrink {

    }
  }
}