package com.example.ntiteamtestapp.domain.useCase

import com.example.ntiteamtestapp.domain.MainScreenRepository
import com.example.ntiteamtestapp.domain.model.ErrorStatus
import com.example.ntiteamtestapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCaseImpl(private val repository: MainScreenRepository) : GetAllProductsUseCase {

    override suspend fun execute(): Flow<Pair<List<Product>?, ErrorStatus?>> {
        return repository.getAllProductsUseCase()
    }
}