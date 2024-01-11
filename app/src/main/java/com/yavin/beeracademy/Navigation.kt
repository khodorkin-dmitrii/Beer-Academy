package com.yavin.beeracademy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.yavin.beeracademy.domain.Beer
import com.yavin.beeracademy.presentation.BeerDetailsScreen
import com.yavin.beeracademy.presentation.BeerDetailsViewModel
import com.yavin.beeracademy.presentation.BeerListScreen
import com.yavin.beeracademy.presentation.BeerListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.BeerListScreen.route
    ) {
        composable(route = Screen.BeerListScreen.route) {
            val viewModel = hiltViewModel<BeerListViewModel>()
            val beers = viewModel.pagingFlow.collectAsLazyPagingItems()
            BeerListScreen(
                beers = beers,
                onItemClick = { beerId ->
                    navController.navigate(Screen.BeerDetailScreen.withArgs(beerId))
                }
            )
        }

        composable(
            route = Screen.BeerDetailScreen.route + "/{beerId}",
            arguments = listOf(
                navArgument("beerId") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            entry.arguments?.getInt("beerId")?.let { beerId ->
                val viewModel = hiltViewModel<BeerDetailsViewModel>()
                var beer: Beer? by remember { mutableStateOf(null) }
                LaunchedEffect(key1 = beerId) {
                    beer = viewModel.getBeerById(beerId)
                }
                beer?.let {
                    BeerDetailsScreen(
                        beer = it,
                        onBackPress = { navController.popBackStack() })
                }
            }
        }
    }
}