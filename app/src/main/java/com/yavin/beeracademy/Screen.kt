package com.yavin.beeracademy

sealed class Screen(val route: String) {
    data object BeerListScreen : Screen("beer_list")
    data object BeerDetailScreen : Screen("beer_detail")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    companion object { // hmm
        const val BEER_LIST = "beer_list"
        const val BEER_DETAIL = "beer_detail"
    }
}