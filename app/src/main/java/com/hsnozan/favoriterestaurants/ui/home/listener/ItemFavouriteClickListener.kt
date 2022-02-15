package com.hsnozan.favoriterestaurants.ui.home.listener

import com.hsnozan.favoriterestaurants.data.model.Restaurant

/**
 * Created by ozan.al on 14.02.2022.
 */
interface ItemFavouriteClickListener {

    fun onFavouriteButtonClick(model: Restaurant, index: Int)
}