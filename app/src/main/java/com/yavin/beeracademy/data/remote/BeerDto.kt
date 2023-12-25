package com.yavin.beeracademy.data.remote

// Network level model
data class BeerDto(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String?
)
