package com.example.ntiteamtestapp.data

import com.example.ntiteamtestapp.data.dto.CategoriesRequest
import com.example.ntiteamtestapp.data.dto.CategoriesResponse
import com.example.ntiteamtestapp.data.dto.ProductsRequest
import com.example.ntiteamtestapp.data.dto.ProductsResponse
import com.example.ntiteamtestapp.data.network.NetworkClient
import com.example.ntiteamtestapp.domain.MainScreenRepository
import com.example.ntiteamtestapp.domain.model.Category
import com.example.ntiteamtestapp.domain.model.ErrorStatus
import com.example.ntiteamtestapp.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainScreenRepositoryImpl(
    private val networkClient: NetworkClient,
) : MainScreenRepository {

    override fun getCategories(): Flow<Pair<List<Category>?, ErrorStatus?>> = flow {
        val response = networkClient.doRequest(CategoriesRequest())
        when (response.resultCode) {
            -1 -> emit(Pair(null, ErrorStatus.NO_CONNECTION))
            200 -> emit(Pair((response as CategoriesResponse).results, null))
            else -> emit(Pair(null, ErrorStatus.ERROR_OCCURRED))
        }
    }

    override fun getAllProducts(): Flow<Pair<List<Product>?, ErrorStatus?>> = flow {
        val response = networkClient.doRequest(ProductsRequest())
        when (response.resultCode) {
            -1 -> emit(Pair(null, ErrorStatus.NO_CONNECTION))
            200 -> emit(Pair((response as ProductsResponse).results, null))
            else -> emit(Pair(null, ErrorStatus.ERROR_OCCURRED))
        }
    }
}