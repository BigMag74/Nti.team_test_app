package com.example.ntiteamtestapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("products_table")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val categoryId: Int,
    val name: String,
    val description: String,
    val image: String,
    val priceCurrent: Int,
    val priceOld: Int?,
    val measure: Int,
    val measureUnit: String,
    val energyPer100Grams: Float,
    val proteinsPer100Grams: Float,
    val fatsPer100Grams: Float,
    val carbohydratesPer100Grams: Float,
    val tagIds: String,
)