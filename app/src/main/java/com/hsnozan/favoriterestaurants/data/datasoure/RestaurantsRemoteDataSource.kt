package com.hsnozan.favoriterestaurants.data.datasoure

import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.data.model.RestaurantListModel
import com.hsnozan.favoriterestaurants.util.getJsonDataFromAsset
import javax.inject.Inject

/**
 * Created by ozan.al on 15.02.2022.
 */
class RestaurantsRemoteDataSource @Inject constructor() {
    private val restaurantListModel = getJsonDataFromAsset<RestaurantListModel>("sample.json")

    fun getAll(): MutableList<Restaurant> {
        return restaurantListModel.restaurants.toMutableList()
    }
}