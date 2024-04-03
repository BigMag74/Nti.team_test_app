package com.example.ntiteamtestapp.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductInformationScreen(productId: Int?) {
    Text(text = "информация о продукте $productId")
}