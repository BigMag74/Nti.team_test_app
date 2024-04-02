package com.example.ntiteamtestapp.domain.useCase

import com.example.ntiteamtestapp.domain.MainScreenRepository
import com.example.ntiteamtestapp.domain.model.Category
import com.example.ntiteamtestapp.domain.model.ErrorStatus
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCaseImpl(private val repository: MainScreenRepository) : GetCategoriesUseCase {

    override suspend fun execute(): Flow<Pair<List<Category>?, ErrorStatus?>> {
        return repository.getCategories()
    }
}