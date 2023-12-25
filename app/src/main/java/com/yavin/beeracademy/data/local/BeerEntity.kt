package com.yavin.beeracademy.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// DB level model
@Entity
data class BeerEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String?
)
