package com.example.ntiteamtestapp.domain

import com.example.ntiteamtestapp.domain.model.Category
import com.example.ntiteamtestapp.domain.model.ErrorStatus
import kotlinx.coroutines.flow.Flow

interface MainScreenRepository {

    fun getCategories(): Flow<Pair<List<Category>?, ErrorStatus?>>

    fun getProductsByCategoryId(categoryId: Int)
}