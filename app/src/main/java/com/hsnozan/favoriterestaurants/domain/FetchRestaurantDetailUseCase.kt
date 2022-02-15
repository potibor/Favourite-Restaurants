package com.hsnozan.favoriterestaurants.domain

import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.data.repository.RestaurantRepository
import com.hsnozan.favoriterestaurants.util.UseCase
import javax.inject.Inject

/**
 * Created by ozan.al on 15.02.2022.
 */

class FetchRestaurantDetailUseCase @Inject constructor(
    private val repository: RestaurantRepository
) : UseCase<Restaurant, FetchRestaurantDetailUseCase.Params>() {

    override suspend fun buildUseCase(params: Params): Restaurant {
        return repository.fetchRestaurantDetail(params.id)
    }

    data class Params(val id: Int)
}