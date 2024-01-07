package com.yavin.beeracademy.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BeerDetailsScreen(beerId: Int) {
    Text(text = "$beerId")

}