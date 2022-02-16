package com.hsnozan.favoriterestaurants.data.model

import com.hsnozan.favoriterestaurants.base.ListAdapterItem
import com.hsnozan.favoriterestaurants.data.database.entity.RestaurantEntity

data class Restaurant(
    override val id: Int,
    val name: String,
    val sortingValues: SortingValues?,
    val status: String,
    var isMovieFavourited: Int = 0
) : ListAdapterItem {
    fun toEntityModel(): RestaurantEntity {
        return RestaurantEntity(
            id = id,
            name = name,
            status = status,
            isMovieFavourited = isMovieFavourited,
            sortingValues = sortingValues?.toEntityModel()
        )
    }
}

enum class StatusValueType(val value: String) {
    OPEN("open"),
    ORDER_AHEAD("order ahead"),
    CLOSED("closed")
}

enum class SortingValueType(val value: String) {
    AVERAGE_PRODUCT_PRICE("averageProductPrice"),
    BEST_MATCH("bestMatch"),
    DELIVERY_COSTS("deliveryCosts"),
    DISTANCE("distance")
}