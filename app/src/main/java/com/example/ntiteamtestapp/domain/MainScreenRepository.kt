package com.example.ntiteamtestapp.domain

import com.example.ntiteamtestapp.data.db.ProductEntity
import com.example.ntiteamtestapp.domain.model.Category
import com.example.ntiteamtestapp.domain.model.ErrorStatus
import com.example.ntiteamtestapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface MainScreenRepository {

    fun getCategories(): Flow<Pair<List<Category>?, ErrorStatus?>>

    fun getAllProducts(): Flow<Pair<List<Product>?, ErrorStatus?>>

    suspend fun addProductsToDB(products: ProductEntity)

    suspend fun deleteProductFromDB(product: ProductEntity)

    suspend fun deleteAllProductsFromDB()
}