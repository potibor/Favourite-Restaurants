package com.hsnozan.favoriterestaurants.ui.dialog

import com.hsnozan.favoriterestaurants.data.model.SortingValueType

/**
 * Created by ozan.al on 16.02.2022.
 */
interface OnDialogClickListener {

    fun onSelect(type: SortingValueType)
}