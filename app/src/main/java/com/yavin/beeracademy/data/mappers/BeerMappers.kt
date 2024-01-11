package com.yavin.beeracademy.data.mappers

import com.yavin.beeracademy.data.local.ArrayListConverter
import com.yavin.beeracademy.data.local.BeerEntity
import com.yavin.beeracademy.data.remote.BeerDto
import com.yavin.beeracademy.domain.Beer

private val converter = ArrayListConverter()
fun BeerDto.toBeerEntity(): BeerEntity {

    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url,
        foodPairing = converter.arrayListToString(food_pairing),
        brewersTips = brewers_tips
    )
}

fun BeerEntity.toBeer(): Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl,
        foodPairing = converter.stringToArrayList(foodPairing),
        brewersTips = brewersTips
    )
}