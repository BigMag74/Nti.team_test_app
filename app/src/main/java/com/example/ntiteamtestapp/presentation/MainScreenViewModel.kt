package com.example.ntiteamtestapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ntiteamtestapp.domain.model.Category
import com.example.ntiteamtestapp.domain.model.Product
import com.example.ntiteamtestapp.domain.useCase.GetAllProductsUseCase
import com.example.ntiteamtestapp.domain.useCase.GetCategoriesUseCase
import kotlinx.coroutines.launch

class MainScreenViewModel(
    getCategoriesUseCase: GetCategoriesUseCase,
    getAllProductsUseCase: GetAllProductsUseCase,

) : ViewModel() {

    val categories: MutableState<List<Category>> = mutableStateOf(listOf())
    val products: MutableState<List<Product>> = mutableStateOf(listOf())
    val firstCategoryId: MutableState<Int> = mutableIntStateOf(0)

    init {
        viewModelScope.launch {
            getCategoriesUseCase.execute().collect {
                when {
                    it.first == null -> {}
                    it.second == null -> {
                        categories.value = it.first!!
                        firstCategoryId.value = it.first!![0].id
                    }
                }
            }
            getAllProductsUseCase.execute().collect {
                when {
                    it.first == null -> {}
                    it.second == null -> products.value = it.first!!
                }
            }
        }
    }


}