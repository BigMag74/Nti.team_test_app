package com.example.ntiteamtestapp.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.ntiteamtestapp.domain.model.Product

@Composable
fun CartScreen(viewmodel: MainScreenViewModel) {
    viewmodel.getProductsFromDB()
    val cartProducts: List<Product> = viewmodel.productFromBD.value
    LazyColumn {
        items(cartProducts) { product ->
            Text(text = product.name)
        }

    }
}