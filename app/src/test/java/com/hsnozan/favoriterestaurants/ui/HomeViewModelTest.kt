package com.hsnozan.favoriterestaurants.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hsnozan.favoriterestaurants.data.model.SortingValueType
import com.hsnozan.favoriterestaurants.domain.FetchRestaurantsUseCase
import com.hsnozan.favoriterestaurants.domain.UpdateRestaurantUseCase
import com.hsnozan.favoriterestaurants.ui.home.HomeViewModel
import com.hsnozan.favoriterestaurants.util.CoroutineTestRule
import com.hsnozan.favoriterestaurants.util.Either
import com.hsnozan.favoriterestaurants.util.TestUtil
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ozan.al on 16.02.2022.
 */

@RunWith(MockitoJUnitRunner.Silent::class)
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var fetchRestaurantsUseCase: FetchRestaurantsUseCase

    @Mock
    lateinit var updateRestaurantUseCase: UpdateRestaurantUseCase

    @InjectMocks
    lateinit var homeViewModel: HomeViewModel

    @Test
    fun `Set error data when fetch restaurants is called`() = runTest {
        whenever(fetchRestaurantsUseCase.run(Unit)).thenReturn(Either.Left(TestUtil.mockFailureModel))

        homeViewModel.fetchRestaurants()

        homeViewModel.onError.observeForever {
            assertThat(it).isEqualTo("Fetch failed")
        }
    }

    @Test
    fun `Set restaurants data when fetch restaurants is called`() = runTest {
        whenever(fetchRestaurantsUseCase.run(Unit))
            .thenReturn(Either.Right(TestUtil.restaurantList.toMutableList()))

        homeViewModel.fetchRestaurants()

        homeViewModel.restaurantsLiveData.observeForever {
            assertThat(it[0].name).isEqualTo("example")
        }
    }

    @Test
    fun `Set error data when update model is called`() = runTest {
        whenever(
            updateRestaurantUseCase.run(
                UpdateRestaurantUseCase.Params(TestUtil.mockRestaurantModel)
            )
        ).thenReturn(Either.Left(TestUtil.mockFailureModel))

        homeViewModel.updateModel(TestUtil.mockRestaurantModel)

        homeViewModel.onError.observeForever {
            assertThat(it).isEqualTo("Fetch failed")
        }
    }

    @Test
    fun `get -1 value when status is open`() {
        val response = homeViewModel.filterByStatus("open")

        assertThat(-1).isEqualTo(response)
    }

    @Test
    fun `get 0 value when status is open`() {
        val response = homeViewModel.filterByStatus("order ahead")

        assertThat(0).isEqualTo(response)
    }

    @Test
    fun `get 1 value when status is open`() {
        val response = homeViewModel.filterByStatus("closed")

        assertThat(1).isEqualTo(response)
    }

    @Test
    fun `get average product price value when filterBySorting is called`() {
        val response = homeViewModel.filterBySorting(
            SortingValueType.AVERAGE_PRODUCT_PRICE, TestUtil.mockRestaurantModel
        )

        assertThat(3.0).isEqualTo(response)
    }

    @Test
    fun `get best match value when filterBySorting is called`() {
        val response = homeViewModel.filterBySorting(
            SortingValueType.BEST_MATCH, TestUtil.mockRestaurantModel
        )

        assertThat(2.0).isEqualTo(response)
    }

    @Test
    fun `get filtered list when filter is called`() {
        homeViewModel.filterList(
            SortingValueType.BEST_MATCH,
            TestUtil.restaurantList.toMutableList()
        )

        homeViewModel.restaurantsLiveData.observeForever {
            assertThat(it[0].name).isEqualTo("example2")
        }
    }
}