package com.quintallabs.randomdrink.android.ui.components.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
  val route: String,
  val title: String,
  val icon: ImageVector
) {
  data object HomeScreen : Destinations(
    route = "home_screen",
    title = "Home",
    icon = Icons.Outlined.Home
  )

  data object Favourite : Destinations(
    route = "favorite_screen",
    title = "Favoritos",
    icon = Icons.Default.FavoriteBorder
  )

  data object Search : Destinations(
    route = "search_screen",
    title = "Search",
    icon = Icons.Outlined.Search
  )

  data object DrinkDetail : Destinations(
    route = "drink_detail_screen/{id}",
    title = "Drink Detail",
    icon = Icons.Outlined.Info
  )

}