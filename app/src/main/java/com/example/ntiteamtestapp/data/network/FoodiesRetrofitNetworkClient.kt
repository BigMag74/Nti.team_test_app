package com.example.ntiteamtestapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.ntiteamtestapp.data.NetworkClient
import com.example.ntiteamtestapp.data.dto.CategoriesRequest
import com.example.ntiteamtestapp.data.dto.CategoriesResponse
import com.example.ntiteamtestapp.data.dto.ProductsRequest
import com.example.ntiteamtestapp.data.dto.ProductsResponse
import com.example.ntiteamtestapp.data.dto.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodiesRetrofitNetworkClient(
    private val foodiesApiService: FoodiesApi,
    private val context: Context,
) : NetworkClient {

    override suspend fun doRequest(dto: Any): Response {
        if (isConnected() == false)
            return Response().apply { resultCode = -1 }

        if (dto is CategoriesRequest) {

            return withContext(Dispatchers.IO) {
                try {
                    val response = CategoriesResponse(foodiesApiService.getCategories())
                    response.apply { resultCode = 200 }
                } catch (e: Throwable) {
                    Response().apply { resultCode = 500 }
                }
            }
        }
        if (dto is ProductsRequest) {
            return withContext(Dispatchers.IO) {
                try {
                    val response = ProductsResponse(foodiesApiService.getAllProducts())
                    response.apply { resultCode = 200 }
                } catch (e: Throwable) {
                    Response().apply { resultCode = 500 }
                }
            }
        } else {
            return Response().apply { resultCode = 500 }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}