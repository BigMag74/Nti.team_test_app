package com.example.ntiteamtestapp.domain.useCase.impl

import com.example.ntiteamtestapp.domain.MainScreenRepository
import com.example.ntiteamtestapp.domain.ProductConverter
import com.example.ntiteamtestapp.domain.model.Product
import com.example.ntiteamtestapp.domain.useCase.GetProductsFromDBUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProductsFromDBUseCaseImpl(
    private val repository: MainScreenRepository,
    private val mapper: ProductConverter,
) : GetProductsFromDBUseCase {

    override suspend fun execute(): Flow<List<Product>> {
        return repository.getProductsFromDB().map { list ->
            list.map { mapper.mapProductEntityToProduct(it) }
        }
    }
}