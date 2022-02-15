package com.hsnozan.favoriterestaurants.data.database.dao

import androidx.room.*
import com.hsnozan.favoriterestaurants.data.database.entity.RestaurantEntity

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

    @Update
    suspend fun updateRestaurant(restaurantEntity: RestaurantEntity)

    @Query("SELECT * FROM restaurant WHERE id=:id")
    suspend fun getRestaurant(id: Int): RestaurantEntity
}