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

    suspend fun getAll(): List<Restaurant> {
        return restaurantsDao.getRestaurants().map {
            RestaurantEntity(
                id = it.id,
                name = it.name,
                status = it.status,
                sortingValues = it.sortingValues,
                isMovieFavourited = it.isMovieFavourited
            ).toRestaurantModel()
        }
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

    suspend fun getRestaurant(id: Int): Restaurant {
        return restaurantsDao.getRestaurant(id).toRestaurantModel()
    }

    suspend fun addAllRestaurantsFromJson(responseFromJson: MutableList<Restaurant>) {
        val list = responseFromJson.map {
            it.toEntityModel()
        }
        restaurantsDao.addAllRestaurantsFromJson(list)
    }
}
