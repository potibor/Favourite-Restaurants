package com.hsnozan.favoriterestaurants.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import com.hsnozan.favoriterestaurants.R
import com.hsnozan.favoriterestaurants.base.BaseListAdapter
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.databinding.ItemHomeBinding
import com.hsnozan.favoriterestaurants.ui.home.listener.ItemFavouriteClickListener

/**
 * Created by ozan.al on 12.02.2022.
 */
@SuppressLint("UseCompatLoadingForDrawables")
class HomeAdapter(
    private val context: Context,
    private val listener: ItemFavouriteClickListener
) : BaseListAdapter<ItemHomeBinding, Restaurant>() {

    override val layoutRes = R.layout.item_home

    override fun bind(binding: ItemHomeBinding, item: Restaurant, position: Int) = with(binding) {
        restaurantName.text = item.name
        restaurantStatus.text = item.status
        favouriteButton.background = setBackgroundOfButton(item.isMovieFavourited)

        favouriteButton.setOnClickListener {
            item.isMovieFavourited = if (item.isMovieFavourited == 0) 1 else 0
            notifyItemChanged(position)
            listener.onFavouriteButtonClick(item, position)
        }
    }

    private fun setBackgroundOfButton(isMovieFavourited: Int): Drawable? {
        return when (isMovieFavourited) {
            1 -> context.getDrawable(R.drawable.ic_star)
            else -> context.getDrawable(R.drawable.ic_empty_star)
        }
    }
}