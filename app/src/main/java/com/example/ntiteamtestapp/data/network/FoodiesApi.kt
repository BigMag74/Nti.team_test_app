package com.example.ntiteamtestapp.data.network

import com.example.ntiteamtestapp.domain.model.Category
import retrofit2.http.GET

interface FoodiesApi {

    @GET("Categories.json")
    suspend fun getCategories(): List<Category>
}