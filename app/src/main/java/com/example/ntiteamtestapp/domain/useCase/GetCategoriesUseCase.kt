package com.example.ntiteamtestapp.domain.useCase

import com.example.ntiteamtestapp.domain.model.Category
import com.example.ntiteamtestapp.domain.model.ErrorStatus
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase {
    suspend fun execute(): Flow<Pair<List<Category>?, ErrorStatus?>>
}