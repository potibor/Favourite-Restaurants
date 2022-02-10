package com.hsnozan.favoriterestaurants.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hsnozan.favoriterestaurants.data.database.dao.RestaurantsDao
import com.hsnozan.favoriterestaurants.data.database.entity.RestaurantEntity

/**
 * Created by hsnozan on 9.02.2022.
 */

@Database(
    entities = [RestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RestaurantsDatabase : RoomDatabase() {

    abstract fun restaurantsDao(): RestaurantsDao
}