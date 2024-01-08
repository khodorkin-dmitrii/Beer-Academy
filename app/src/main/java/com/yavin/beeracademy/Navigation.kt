package com.yavin.beeracademy

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yavin.beeracademy.presentation.BeerDetailsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.BeerListScreen.route
    ) {
        composable(route = Screen.BeerListScreen.route) {
//            BeerListScreen(beers = )
        }
        composable(
            route = Screen.BeerDetailScreen.route+"/{beerId}",
            arguments = listOf(
                navArgument("beerId") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            entry.arguments?.getInt("beerId")?.let {
                BeerDetailsScreen(beerId = it)
            }
        }
    }
}