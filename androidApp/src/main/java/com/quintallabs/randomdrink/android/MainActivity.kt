package com.quintallabs.randomdrink.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.quintallabs.randomdrink.android.presentation.favorites.FavoriteScreen
import com.quintallabs.randomdrink.android.presentation.home.HomeScreen
import com.quintallabs.randomdrink.android.presentation.search.SearchScreen
import com.quintallabs.randomdrink.android.presentation.search.detail.DrinkDetailScreen
import com.quintallabs.randomdrink.android.ui.components.BottomBar
import com.quintallabs.randomdrink.android.ui.components.models.Destinations
import com.quintallabs.randomdrink.android.ui.theme.RandomDrinkTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomDrinkTheme(
                dynamicColor = false
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    val shouldShowBottomNavigation = when (currentRoute(navController)) {
                        Destinations.HomeScreen.route,
                        Destinations.Favourite.route,
                        Destinations.Search.route,
                        -> true

                        else -> false
                    }

                    Scaffold(
                        bottomBar = {
                            if (shouldShowBottomNavigation)
                                BottomBar(
                                    navController = navController,
                                    modifier = Modifier
                                )
                        }) { paddingValues ->
                        Box(
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            NavigationGraph(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            HomeScreen()
        }
        composable(Destinations.Favourite.route) {
            FavoriteScreen()
        }
        composable(Destinations.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(
            Destinations.DrinkDetail.route,
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            }
            )) {
            val id = it.arguments?.getString("id")
            DrinkDetailScreen(navController = navController, drinkId = id)
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview
@Composable
fun DefaultPreview() {
    RandomDrinkTheme {

    }
}
