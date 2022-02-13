package com.hsnozan.favoriterestaurants.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsnozan.favoriterestaurants.data.model.Restaurant
import com.hsnozan.favoriterestaurants.domain.FetchRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchRestaurantsUseCase: FetchRestaurantsUseCase
) : ViewModel() {

    private val _restaurantsLiveData = MutableLiveData<List<Restaurant>>()
    val restaurantsLiveData: LiveData<List<Restaurant>> = _restaurantsLiveData

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

    private fun updateUI(response: List<Restaurant>) {
        _restaurantsLiveData.value = response
    }
}