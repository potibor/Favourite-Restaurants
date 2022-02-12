package com.hsnozan.favoriterestaurants.data.model

import com.hsnozan.favoriterestaurants.base.ListAdapterItem

data class Restaurant(
        override val id: Int,
        val name: String,
        val sortingValues: SortingValues?,
        val status: String
) : ListAdapterItem