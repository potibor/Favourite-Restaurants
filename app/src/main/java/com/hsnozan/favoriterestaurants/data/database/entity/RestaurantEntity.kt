package com.hsnozan.favoriterestaurants.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.data.model.SortingValues

/**
 * Created by hsnozan on 9.02.2022.
 */

@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val isMovieFavourited: Int = 0,
    val sortingValues: SortingValuesEntity? = SortingValuesEntity()
) {
    fun toRestaurantModel() = Restaurant(
        id = id,
        name = name,
        sortingValues = sortingValues?.toSortingValuesModel(),
        status = status,
        isMovieFavourited = isMovieFavourited
    )
}

data class SortingValuesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bestMatch") val bestMatch: Double = 0.0,
    val newest: Double = 0.0,
    val ratingAverage: Double = 0.0,
    val distance: Int = 0,
    val popularity: Double = 0.0,
    val averageProductPrice: Int = 0,
    val deliveryCosts: Int = 0,
    val minCost: Int = 0,
) {
    fun toSortingValuesModel() = SortingValues(
        bestMatch = bestMatch,
        newest = newest,
        ratingAverage = ratingAverage,
        distance = distance,
        popularity = popularity,
        averageProductPrice = averageProductPrice,
        deliveryCosts = deliveryCosts,
        minCost = minCost
    )
}