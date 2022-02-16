package com.hsnozan.favoriterestaurants.util

import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.data.model.RestaurantListModel
import com.hsnozan.favoriterestaurants.data.model.SortingValues

/**
 * Created by ozan.al on 16.02.2022.
 */
class TestUtil {

    companion object {
        private val mockSortingValues = SortingValues(
            averageProductPrice = 3,
            bestMatch = 2.0,
            deliveryCosts = 3,
            distance = 3,
            minCost = 3,
            newest = 2.0,
            popularity = 2.0,
            ratingAverage = 2.0
        )
        private val mockSortingValues2 = SortingValues(
            averageProductPrice = 35,
            bestMatch = 24.0,
            deliveryCosts = 3,
            distance = 3,
            minCost = 3,
            newest = 2.0,
            popularity = 2.0,
            ratingAverage = 2.0
        )
        private val mockSortingValues3 = SortingValues(
            averageProductPrice = 3,
            bestMatch = 22.0,
            deliveryCosts = 3,
            distance = 3,
            minCost = 3,
            newest = 2.0,
            popularity = 2.0,
            ratingAverage = 2.0
        )

        val mockRestaurantModel = Restaurant(
            0,
            "example",
            sortingValues = mockSortingValues,
            status = "open",
            isMovieFavourited = 1
        )

        private val mockRestaurantModel2 = Restaurant(
            0,
            "example2",
            sortingValues = mockSortingValues2,
            status = "open",
            isMovieFavourited = 1
        )

        private val mockRestaurantModel3 = Restaurant(
            0,
            "example3",
            sortingValues = mockSortingValues3,
            status = "open",
            isMovieFavourited = 1
        )

        val restaurantList = buildList {
            add(mockRestaurantModel)
            add(mockRestaurantModel2)
            add(mockRestaurantModel3)
        }

        val emptyRestaurantList = emptyList<Restaurant>()

        val restaurantListModelFromJson = getJsonDataFromAsset<RestaurantListModel>("sample.json")

        val mockFailureModel = Throwable("Fetch failed")
    }
}