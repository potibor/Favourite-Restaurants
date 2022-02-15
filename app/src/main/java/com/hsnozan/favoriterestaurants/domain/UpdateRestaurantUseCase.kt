package com.hsnozan.favoriterestaurants.domain

import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.data.repository.RestaurantRepository
import com.hsnozan.favoriterestaurants.domain.UpdateRestaurantUseCase.Params
import com.hsnozan.favoriterestaurants.util.UseCase
import javax.inject.Inject

/**
 * Created by ozan.al on 14.02.2022.
 */
class UpdateRestaurantUseCase @Inject constructor(
    private val repository: RestaurantRepository
) : UseCase<Unit, Params>() {

    override suspend fun buildUseCase(params: Params) {
        repository.updateRestaurant(params.restaurant)
    }

    data class Params(
        val restaurant: Restaurant
    )
}