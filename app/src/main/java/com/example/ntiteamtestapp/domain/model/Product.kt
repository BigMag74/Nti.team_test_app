package com.example.ntiteamtestapp.domain.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    @SerializedName("category_id")
    val categoryId: Int,
    val name: String,
    val description: String,
    val image: String,
    @SerializedName("price_current")
    val priceCurrent: Int,
    @SerializedName("price_old")
    val priceOld: Int?,
    val measure: Int,
    @SerializedName("measure_unit")
    val measureUnit: String,
    @SerializedName("energy_per_100_grams")
    val energyPer100Grams: Float,
    @SerializedName("proteins_per_100_grams")
    val proteinsPer100Grams: Float,
    @SerializedName("fats_per_100_grams")
    val fatsPer100Grams: Float,
    @SerializedName("carbohydrates_per_100_grams")
    val carbohydratesPer100Grams: Float,
    val tagIds: List<Int>?,
)
