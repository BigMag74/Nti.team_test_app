package com.example.ntiteamtestapp.domain.useCase.impl

import com.example.ntiteamtestapp.domain.MainScreenRepository
import com.example.ntiteamtestapp.domain.ProductConverter
import com.example.ntiteamtestapp.domain.model.Product
import com.example.ntiteamtestapp.domain.useCase.AddProductToDBUseCase

class AddProductToDBUseCaseImpl(private val repository: MainScreenRepository,
                                private val productConverter: ProductConverter) : AddProductToDBUseCase {

    override suspend fun execute(product: Product) {
        repository.addProductsToDB(productConverter.mapProductToProductEntity(product))
    }
}