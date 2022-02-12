package com.hsnozan.favoriterestaurants.ui.home.adapter

import com.hsnozan.favoriterestaurants.R
import com.hsnozan.favoriterestaurants.base.BaseListAdapter
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.databinding.ItemHomeBinding

/**
 * Created by ozan.al on 12.02.2022.
 */
class HomeAdapter : BaseListAdapter<ItemHomeBinding, Restaurant>() {

    override val layoutRes = R.layout.item_home

    override fun bind(binding: ItemHomeBinding, item: Restaurant) = with(binding) {
        restaurantName.text = item.name
        restaurantStatus.text = item.status
    }
}