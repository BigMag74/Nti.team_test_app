package com.example.ntiteamtestapp.domain.model

data class ProductCard(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val imageUrl: String,
    val measure: Int,
    val measureUnit: String,
    val priceCurrent: Int,
    val priceOld: Int?,
    val tagIds: List<Int>,
)
