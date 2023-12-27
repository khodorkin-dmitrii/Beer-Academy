package com.yavin.beeracademy.domain

import androidx.compose.runtime.Immutable

// Domain level model
@Immutable
data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String?,
    val imageUrl: String?
)
