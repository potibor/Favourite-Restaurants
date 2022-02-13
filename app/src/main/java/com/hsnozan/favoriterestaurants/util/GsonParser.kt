package com.hsnozan.favoriterestaurants.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by ozan.al on 13.02.2022.
 */
val gson: Gson = GsonBuilder().setLenient().create()

inline fun <reified T> getJsonDataFromAsset(fileName: String): T {
    val type = object : TypeToken<T>() {}.type
    return gson.fromJson(gson.getResourceReader(fileName), type)
}

fun Gson.getResourceReader(fileName: String): BufferedReader {
    val resource = javaClass.classLoader!!.getResourceAsStream(fileName)
    return BufferedReader(InputStreamReader(resource, Charsets.UTF_8))
}