package com.hsnozan.favoriterestaurants.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.domain.FetchRestaurantsUseCase
import com.hsnozan.favoriterestaurants.domain.UpdateRestaurantUseCase
import com.hsnozan.favoriterestaurants.ui.home.listener.ItemFavouriteClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchRestaurantsUseCase: FetchRestaurantsUseCase,
    private val updateRestaurantUseCase: UpdateRestaurantUseCase
) : ViewModel(), ItemFavouriteClickListener {

    private val _restaurantsLiveData = MutableLiveData<MutableList<Restaurant>>()
    val restaurantsLiveData: LiveData<MutableList<Restaurant>> = _restaurantsLiveData

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
}