package com.hsnozan.favoriterestaurants.data.repository

import com.hsnozan.favoriterestaurants.data.datasoure.RestaurantsLocalDataSource
import com.hsnozan.favoriterestaurants.data.datasoure.RestaurantsRemoteDataSource
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import javax.inject.Inject

/**
 * Created by hsnozan on 8.02.2022.
 */
class RestaurantRepository @Inject constructor(
    private val localDataSource: RestaurantsLocalDataSource,
    private val remoteDataSource: RestaurantsRemoteDataSource,
) {
    suspend fun fetchRestaurants(): MutableList<Restaurant> {
        val response = localDataSource.getAll()

        if (response.isNullOrEmpty()) {
            val responseFromJson = remoteDataSource.getAll()
            addAllListToLocal(responseFromJson)
            return responseFromJson
        }
        return response.toMutableList()
    }

    suspend fun addAllListToLocal(responseFromJson: MutableList<Restaurant>) {
        return localDataSource.addAllRestaurantsFromJson(responseFromJson)
    }

    suspend fun updateRestaurant(restaurant: Restaurant) {
        return localDataSource.update(restaurant = restaurant)
    }

    suspend fun fetchRestaurantDetail(id: Int): Restaurant {
        return localDataSource.getRestaurant(id)
    }
}