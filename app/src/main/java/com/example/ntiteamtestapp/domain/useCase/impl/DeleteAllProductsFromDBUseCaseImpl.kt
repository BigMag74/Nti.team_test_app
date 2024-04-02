package com.example.ntiteamtestapp.domain.useCase.impl

import com.example.ntiteamtestapp.domain.MainScreenRepository
import com.example.ntiteamtestapp.domain.useCase.DeleteAllProductsFromDBUseCase

class DeleteAllProductsFromDBUseCaseImpl(private val repository: MainScreenRepository) : DeleteAllProductsFromDBUseCase {

    override suspend fun execute() {
        repository.deleteAllProductsFromDB()
    }
}