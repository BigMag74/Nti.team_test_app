package com.example.ntiteamtestapp.domain.useCase

import com.example.ntiteamtestapp.domain.model.Product

interface DeleteProductFromDBUseCase {
    suspend fun execute(product: Product)
}