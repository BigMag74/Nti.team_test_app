package com.example.ntiteamtestapp.domain.useCase

import com.example.ntiteamtestapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface GetProductsFromDBUseCase {

    suspend fun execute(): Flow<List<Product>>
}