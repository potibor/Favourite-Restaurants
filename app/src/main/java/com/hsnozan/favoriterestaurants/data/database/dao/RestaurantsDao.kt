package com.hsnozan.favoriterestaurants.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hsnozan.favoriterestaurants.data.database.entity.RestaurantEntity
import com.hsnozan.favoriterestaurants.data.model.Restaurant

/**
 * Created by hsnozan on 9.02.2022.
 */

@Dao
interface RestaurantsDao {

    @Query("SELECT * FROM restaurant")
    suspend fun getRestaurants(): List<RestaurantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun addAllRestaurantsFromJson(list: List<RestaurantEntity>)

}