package com.quintallabs.randomdrink.android.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.quintallabs.randomdrink.android.ui.components.models.Destinations

@Composable
fun BottomBar(
  navController: NavHostController, modifier: Modifier = Modifier
) {
  val screens = listOf(
    Destinations.HomeScreen, Destinations.Favourite, Destinations.Search
  )

  NavigationBar(
    modifier = modifier,
  ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    screens.forEach { screen ->

      NavigationBarItem(
        label = {
          Text(text = screen.title)
        },
        icon = {
          Icon(imageVector = screen.icon, contentDescription = "")
        },
        selected = currentRoute == screen.route,
        onClick = {
          if(navController.currentDestination?.route.equals(screen.route))
            return@NavigationBarItem

          navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = false
            }
            launchSingleTop = false
            restoreState = false
          }
        },
        colors = NavigationBarItemDefaults.colors(),
      )
    }
  }

}