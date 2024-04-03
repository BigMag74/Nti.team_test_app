package com.example.ntiteamtestapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ntiteamtestapp.domain.model.Category
import com.example.ntiteamtestapp.domain.model.Product
import com.example.ntiteamtestapp.domain.useCase.AddProductToDBUseCase
import com.example.ntiteamtestapp.domain.useCase.DeleteAllProductsFromDBUseCase
import com.example.ntiteamtestapp.domain.useCase.DeleteProductFromDBUseCase
import com.example.ntiteamtestapp.domain.useCase.GetAllProductsUseCase
import com.example.ntiteamtestapp.domain.useCase.GetCategoriesUseCase
import com.example.ntiteamtestapp.domain.useCase.GetProductsFromDBUseCase
import kotlinx.coroutines.launch

class MainScreenViewModel(
    getCategoriesUseCase: GetCategoriesUseCase,
    getAllProductsUseCase: GetAllProductsUseCase,
    private val addProductToDBUseCase: AddProductToDBUseCase,
    private val deleteProductFromDBUseCase: DeleteProductFromDBUseCase,
    private val deleteAllProductsFromDBUseCase: DeleteAllProductsFromDBUseCase,
    private val getProductsFromDBUseCase: GetProductsFromDBUseCase,
) : ViewModel() {

    val categories: MutableState<List<Category>> = mutableStateOf(listOf())
    val products: MutableState<List<Product>> = mutableStateOf(listOf())
    val firstCategoryId: MutableState<Int> = mutableIntStateOf(0)
    val productFromBD: MutableState<List<Product>> = mutableStateOf(listOf())

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
    fun addProductToDB(product: Product) {
        viewModelScope.launch {
            addProductToDBUseCase.execute(product)
        }
    }

    fun deleteProductFromDB(product: Product) {
        viewModelScope.launch {
            deleteProductFromDBUseCase.execute(product)
        }
    }

    fun deleteAllProductsFromDB() {
        viewModelScope.launch {
            deleteAllProductsFromDBUseCase.execute()
        }
    }

    fun getProductsFromDB() {
        viewModelScope.launch {
            getProductsFromDBUseCase.execute().collect {
                productFromBD.value = it
            }
        }
    }
}