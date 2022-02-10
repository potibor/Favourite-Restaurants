package com.hsnozan.favoriterestaurants.di

import android.content.Context
import androidx.room.Room
import com.hsnozan.favoriterestaurants.FavouriteRestaurantsApp
import com.hsnozan.favoriterestaurants.data.database.RestaurantsDatabase
import com.hsnozan.favoriterestaurants.data.database.dao.RestaurantsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by hsnozan on 9.02.2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideContext(): Context = FavouriteRestaurantsApp()

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): RestaurantsDatabase =
        Room.databaseBuilder(
            context,
            RestaurantsDatabase::class.java,
            "restaurants.db"
        ).fallbackToDestructiveMigration().build()


    @Provides
    fun provideLogDao(database: RestaurantsDatabase): RestaurantsDao {
        return database.restaurantsDao()
    }
}