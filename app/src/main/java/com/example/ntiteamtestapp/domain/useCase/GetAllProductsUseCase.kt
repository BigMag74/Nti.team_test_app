package com.example.ntiteamtestapp.domain.useCase

import com.example.ntiteamtestapp.domain.model.ErrorStatus
import com.example.ntiteamtestapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface GetAllProductsUseCase {

    suspend fun execute(): Flow<Pair<List<Product>?, ErrorStatus?>>
}