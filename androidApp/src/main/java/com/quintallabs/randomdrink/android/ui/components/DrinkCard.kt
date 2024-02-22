package com.quintallabs.randomdrink.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quintallabs.randomdrink.domain.models.Drink

@Composable
fun DrinkCard(
  drink: Drink,
  onFavoriteClicked: (Drink) -> Unit
) {

  var isFavoriteIcon by remember {
    mutableStateOf(drink.isFavorite)
  }

  ElevatedCard(
    elevation = CardDefaults.cardElevation(
      defaultElevation = 10.dp
    ),
    modifier = Modifier
      .fillMaxWidth(),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.primary
    )

  ) {
    Column {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
          .heightIn(min = 100.dp)
      ) {
        UrlImage(drinkThumb = drink.drinkThumb, drinkContentDescription = drink.drinkName)
      }

      Column(
        modifier = Modifier
          .padding(16.dp)
      ) {

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          DrinkTitle(drink.drinkName, Modifier.weight(1f))
          IconButton(
            onClick = {
              drink.isFavorite = !isFavoriteIcon
              isFavoriteIcon = !isFavoriteIcon
              onFavoriteClicked(drink)
            }
          ) {
            Icon(
              imageVector = if(drink.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
              contentDescription = "Botao favorito")
          }
        }

        Spacer(modifier = Modifier)

        DrinkPropertyList("Ingredients:", drink.ingredients, drink.measures)

        Spacer(modifier = Modifier)
      }
    }
  }
}