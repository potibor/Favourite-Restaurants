package com.hsnozan.favoriterestaurants.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hsnozan.favoriterestaurants.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}