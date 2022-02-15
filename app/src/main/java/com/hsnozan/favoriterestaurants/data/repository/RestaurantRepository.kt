package com.hsnozan.favoriterestaurants.data.repository

import com.hsnozan.favoriterestaurants.data.datasoure.RestaurantsLocalDataSource
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import javax.inject.Inject

/**
 * Created by hsnozan on 8.02.2022.
 */
class RestaurantRepository @Inject constructor(
    private val localDataSource: RestaurantsLocalDataSource
) {
    suspend fun fetchRestaurants(): MutableList<Restaurant> {
        val response = localDataSource.getAll()
        return response.toMutableList()
    }

    suspend fun updateRestaurant(restaurant: Restaurant) {
        return localDataSource.update(restaurant = restaurant)
    }
}