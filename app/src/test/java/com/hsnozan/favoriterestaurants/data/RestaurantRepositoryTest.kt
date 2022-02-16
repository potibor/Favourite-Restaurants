package com.hsnozan.favoriterestaurants.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hsnozan.favoriterestaurants.data.datasoure.RestaurantsLocalDataSource
import com.hsnozan.favoriterestaurants.data.datasoure.RestaurantsRemoteDataSource
import com.hsnozan.favoriterestaurants.data.repository.RestaurantRepository
import com.hsnozan.favoriterestaurants.util.TestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by ozan.al on 16.02.2022.
 */

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class RestaurantRepositoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var localDataSource: RestaurantsLocalDataSource

    @Mock
    lateinit var remoteDataSource: RestaurantsRemoteDataSource

    @InjectMocks
    lateinit var restaurantRepository: RestaurantRepository

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `fetch restaurants from json when local is empty`() = runTest {
        whenever(localDataSource.getAll()).thenReturn(TestUtil.emptyRestaurantList)
        whenever(remoteDataSource.getAll())
            .thenReturn(TestUtil.restaurantListModelFromJson.restaurants.toMutableList())

        val response = restaurantRepository.fetchRestaurants()

        assertThat(response[0].name).isEqualTo("Tanoshii Sushi")
    }

    @Test
    fun `fetch restaurants from local`() = runTest {
        whenever(localDataSource.getAll()).thenReturn(TestUtil.restaurantList)

        val response = restaurantRepository.fetchRestaurants()

        assertThat(response[0].name).isEqualTo("example")
    }

    @Test
    fun `add all list to local from json`() = runTest {
        val mutableList = TestUtil.restaurantList.toMutableList()

        restaurantRepository.addAllListToLocal(mutableList)

        verify(localDataSource).addAllRestaurantsFromJson(mutableList)
    }

    @Test
    fun `update Restaurant`() = runTest {
        val restaurant = TestUtil.mockRestaurantModel

        restaurantRepository.updateRestaurant(restaurant)

        verify(localDataSource).update(restaurant)
    }
}