package com.hsnozan.favoriterestaurants.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
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
        @ColumnInfo(name = "name") val name: String = "",
        @ColumnInfo(name = "status") val status: String = "",
) {
    @Ignore
    val sortingValuesEntity: SortingValuesEntity? = null

    fun toRestaurantModel() = Restaurant(
            id = id, name = name,
            sortingValues = sortingValuesEntity?.toSortingValuesModel(), status = status
    )
}

@Entity(tableName = "sortingValues")
data class SortingValuesEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "bestMatch") val bestMatch: Double = 0.0,
        @ColumnInfo(name = "newest") val newest: Double = 0.0,
        @ColumnInfo(name = "ratingAverage") val ratingAverage: Double = 0.0,
        @ColumnInfo(name = "distance") val distance: Int = 0,
        @ColumnInfo(name = "popularity") val popularity: Double = 0.0,
        @ColumnInfo(name = "averageProductPrice") val averageProductPrice: Int = 0,
        @ColumnInfo(name = "deliveryCosts") val deliveryCosts: Int = 0,
        @ColumnInfo(name = "minCost") val minCost: Int = 0,
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