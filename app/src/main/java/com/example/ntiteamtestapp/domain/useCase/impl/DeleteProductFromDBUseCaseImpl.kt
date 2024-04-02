package com.example.ntiteamtestapp.domain.useCase.impl

import com.example.ntiteamtestapp.domain.MainScreenRepository
import com.example.ntiteamtestapp.domain.ProductConverter
import com.example.ntiteamtestapp.domain.model.Product
import com.example.ntiteamtestapp.domain.useCase.DeleteProductFromDBUseCase

class DeleteProductFromDBUseCaseImpl(private val repository: MainScreenRepository,
                                     private val converter: ProductConverter
) : DeleteProductFromDBUseCase {

    override suspend fun execute(product: Product) {
        repository.deleteProductFromDB(converter.mapProductToProductEntity(product))
    }
}