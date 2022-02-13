package com.hsnozan.favoriterestaurants.data.model

import com.hsnozan.favoriterestaurants.base.ListAdapterItem
import com.hsnozan.favoriterestaurants.data.database.entity.RestaurantEntity

data class Restaurant(
    override val id: Int,
    val name: String,
    val sortingValues: SortingValues?,
    val status: String
) : ListAdapterItem {
    fun toEntityModel(): RestaurantEntity {
        val entityModel = RestaurantEntity(id = id, name, status)
        entityModel.sortingValuesEntity = sortingValues?.toEntityModel()
        return entityModel
    }
}