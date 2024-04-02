package com.example.ntiteamtestapp.domain.useCase

import com.example.ntiteamtestapp.domain.model.Product

interface AddProductsToDBUseCase {

    suspend fun execute(products: List<Product>)
}