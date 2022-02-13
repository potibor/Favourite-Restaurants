package com.hsnozan.favoriterestaurants.domain

import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.data.repository.RestaurantRepository
import com.hsnozan.favoriterestaurants.util.UseCase
import javax.inject.Inject

/**
 * Created by ozan.al on 13.02.2022.
 */
class FetchRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) : UseCase<List<Restaurant>, Unit>() {

    override suspend fun buildUseCase(params: Unit): List<Restaurant> {
        return repository.fetchRestaurants()
    }
}