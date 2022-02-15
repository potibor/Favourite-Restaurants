package com.hsnozan.favoriterestaurants.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.domain.FetchRestaurantDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fetchRestaurantDetailUseCase: FetchRestaurantDetailUseCase
) : ViewModel() {

    private val _restaurantLiveData = MutableLiveData<Restaurant>()
    val restaurantLiveData: LiveData<Restaurant> = _restaurantLiveData

    fun getRestaurant(id: Int) = viewModelScope.launch {
        fetchRestaurantDetailUseCase.run(
            FetchRestaurantDetailUseCase.Params(id)
        ).either(::handleError, ::updateUI)
    }

    private fun updateUI(restaurant: Restaurant) {
        _restaurantLiveData.value = restaurant
    }

    private fun handleError(throwable: Throwable) {
        throwable.localizedMessage?.let {
            Log.i("TAGG:", it)
        }
    }
}