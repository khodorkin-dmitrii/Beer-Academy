package com.yavin.beeracademy

sealed class Screen(val route: String) {
    data object BeerListScreen : Screen("beer_list")
    data object BeerDetailScreen : Screen("beer_detail")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}