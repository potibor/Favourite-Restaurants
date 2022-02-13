package com.hsnozan.favoriterestaurants.data.model

import com.hsnozan.favoriterestaurants.data.database.entity.SortingValuesEntity

data class SortingValues(
    val averageProductPrice: Int,
    val bestMatch: Double,
    val deliveryCosts: Int,
    val distance: Int,
    val minCost: Int,
    val newest: Double,
    val popularity: Double,
    val ratingAverage: Double
) {
    fun toEntityModel() = SortingValuesEntity(
        bestMatch,
        newest,
        ratingAverage,
        distance,
        popularity,
        averageProductPrice,
        deliveryCosts,
        minCost
    )
}