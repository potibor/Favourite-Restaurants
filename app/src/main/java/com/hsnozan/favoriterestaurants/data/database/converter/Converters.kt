package com.hsnozan.favoriterestaurants.data.database.converter

import androidx.room.TypeConverter
import com.hsnozan.favoriterestaurants.data.database.entity.SortingValuesEntity
import org.json.JSONObject

/**
 * Created by ozan.al on 15.02.2022.
 */

class Converters {

    @TypeConverter
    fun fromSortingValues(sortingValues: SortingValuesEntity): String {
        return JSONObject().apply {
            put("averageProductPrice", sortingValues.averageProductPrice)
            put("bestMatch", sortingValues.bestMatch)
            put("deliveryCosts", sortingValues.deliveryCosts)
            put("distance", sortingValues.distance)
            put("minCost", sortingValues.minCost)
            put("newest", sortingValues.newest)
            put("popularity", sortingValues.popularity)
            put("ratingAverage", sortingValues.ratingAverage)
        }.toString()
    }

    @TypeConverter
    fun toSortingValues(source: String): SortingValuesEntity {
        val json = JSONObject(source)
        return SortingValuesEntity(
            json.getDouble("bestMatch"),
            json.getDouble("newest"),
            json.getDouble("ratingAverage"),
            json.getInt("distance"),
            json.getDouble("popularity"),
            json.getInt("averageProductPrice"),
            json.getInt("deliveryCosts"),
            json.getInt("minCost")
        )
    }
}