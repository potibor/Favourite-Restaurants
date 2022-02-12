package com.hsnozan.favoriterestaurants.base

/**
 * Created by hsnozan on 8.02.2022.
 */
interface ListAdapterItem {
    val id: Int

    override fun equals(other: Any?): Boolean
}