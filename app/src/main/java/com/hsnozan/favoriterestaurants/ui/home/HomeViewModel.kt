package com.hsnozan.favoriterestaurants.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.data.model.SortingValueType
import com.hsnozan.favoriterestaurants.data.model.SortingValueType.*
import com.hsnozan.favoriterestaurants.data.model.StatusValueType.*
import com.hsnozan.favoriterestaurants.domain.FetchRestaurantsUseCase
import com.hsnozan.favoriterestaurants.domain.UpdateRestaurantUseCase
import com.hsnozan.favoriterestaurants.ui.dialog.OnDialogClickListener
import com.hsnozan.favoriterestaurants.ui.home.listener.ItemFavouriteClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchRestaurantsUseCase: FetchRestaurantsUseCase,
    private val updateRestaurantUseCase: UpdateRestaurantUseCase
) : ViewModel(), ItemFavouriteClickListener, OnDialogClickListener {

    private val _restaurantsLiveData = MutableLiveData<MutableList<Restaurant>>()
    val restaurantsLiveData: LiveData<MutableList<Restaurant>> = _restaurantsLiveData

    private val _sortingFilterClicked = MutableLiveData<Double>()
    val sortingFilterClicked: LiveData<Double> = _sortingFilterClicked

    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() = viewModelScope.launch {
        fetchRestaurantsUseCase.run(Unit).either(::handleError, ::updateUI)
    }

    private fun handleError(throwable: Throwable) {
        throwable.localizedMessage?.let {
            Log.i("TAGG:", it)
        }
    }

    private fun updateUI(response: MutableList<Restaurant>) {
        _restaurantsLiveData.value = response
    }

    override fun onFavouriteButtonClick(model: Restaurant, index: Int) {
        updateModel(model)
    }

    private fun updateModel(model: Restaurant) = viewModelScope.launch {
        updateRestaurantUseCase.run(
            UpdateRestaurantUseCase.Params(restaurant = model)
        ).either(::handleError) {
            Log.i("TAGG:", "updated")
        }
    }

    private fun filterList(type: SortingValueType) {
        _restaurantsLiveData.value = _restaurantsLiveData.value?.sortedWith(
            compareByDescending<Restaurant> {
                it.isMovieFavourited
            }.thenBy {
                filterByStatus(it.status)
            }.thenBy { filterBySorting(type, it) })?.toMutableList()
    }

    private fun filterByStatus(status: String): Int {
        return when (status) {
            OPEN.value -> -1
            ORDER_AHEAD.value -> 0
            CLOSED.value -> 1
            else -> Integer.MAX_VALUE
        }
    }

    private fun filterBySorting(type: SortingValueType, restaurant: Restaurant): Double {
        restaurant.sortingValues?.let {
            return when (type) {
                AVERAGE_PRODUCT_PRICE -> it.averageProductPrice.toDouble()
                BEST_MATCH -> it.bestMatch
                DELIVERY_COSTS -> it.deliveryCosts.toDouble()
                DISTANCE -> it.distance.toDouble()
            }
        }
        return restaurant.sortingValues?.bestMatch ?: 0.0
    }

    override fun onSelect(type: SortingValueType) {
        filterList(type)
    }
}