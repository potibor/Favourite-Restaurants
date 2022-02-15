package com.hsnozan.favoriterestaurants.data.datasoure

import com.hsnozan.favoriterestaurants.data.database.dao.RestaurantsDao
import com.hsnozan.favoriterestaurants.data.database.entity.RestaurantEntity
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.data.model.RestaurantListModel
import com.hsnozan.favoriterestaurants.util.getJsonDataFromAsset
import javax.inject.Inject

/**
 * Created by hsnozan on 9.02.2022.
 */
class RestaurantsLocalDataSource @Inject constructor(
    private val restaurantsDao: RestaurantsDao
) {
    private val restaurantListModel = getJsonDataFromAsset<RestaurantListModel>("sample.json")

    suspend fun getAll(): List<Restaurant> {
        val response = restaurantsDao.getRestaurants().map {
            RestaurantEntity(
                id = it.id,
                name = it.name,
                status = it.status,
                sortingValues = it.sortingValues,
                isMovieFavourited = it.isMovieFavourited
            ).toRestaurantModel()
        }

        if (response.isNullOrEmpty()) {
            val responseFromJson = restaurantListModel.restaurants.map {
                it.toEntityModel()
            }
            restaurantsDao.addAllRestaurantsFromJson(responseFromJson)
            return restaurantListModel.restaurants
        }
        return response
    }

    suspend fun update(restaurant: Restaurant) = with(restaurant) {
        restaurantsDao.updateRestaurant(
            RestaurantEntity(
                id = id,
                name = name,
                sortingValues = sortingValues?.toEntityModel(),
                status = status,
                isMovieFavourited = isMovieFavourited
            )
        )
    }
}
