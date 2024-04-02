package com.example.ntiteamtestapp.domain.useCase

import com.example.ntiteamtestapp.domain.model.Product

interface AddProductToDBUseCase {

    suspend fun execute(product: Product)
}