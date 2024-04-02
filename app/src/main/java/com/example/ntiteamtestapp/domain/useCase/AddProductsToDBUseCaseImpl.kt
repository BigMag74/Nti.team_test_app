package com.example.ntiteamtestapp.domain.useCase

import com.example.ntiteamtestapp.domain.MainScreenRepository
import com.example.ntiteamtestapp.domain.ProductConverter
import com.example.ntiteamtestapp.domain.model.Product

class AddProductsToDBUseCaseImpl(private val repository: MainScreenRepository,
                                 private val productConverter: ProductConverter) : AddProductsToDBUseCase {

    override suspend fun execute(products: List<Product>) {
        return repository.addProductsToDBUseCase(products.map { product ->
            productConverter.mapProductToProductEntity(product)
        })
    }
}