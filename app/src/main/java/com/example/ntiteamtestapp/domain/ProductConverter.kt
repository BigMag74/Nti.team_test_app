package com.example.ntiteamtestapp.domain

import androidx.room.TypeConverter
import com.example.ntiteamtestapp.data.db.ProductEntity
import com.example.ntiteamtestapp.domain.model.Product

class ProductConverter {

    @TypeConverter
    fun mapProductToProductEntity(product: Product): ProductEntity {
        return ProductEntity(
            product.id,
            product.categoryId,
            product.name,
            product.description,
            product.image,
            product.priceCurrent,
            product.priceOld,
            product.measure,
            product.measureUnit,
            product.energyPer100Grams,
            product.proteinsPer100Grams,
            product.fatsPer100Grams,
            product.carbohydratesPer100Grams,
            product.tagIds.joinToString(separator = ","),
            product.productsInCart
        )
    }

    @TypeConverter
    fun mapProductEntityToProduct(productEntity: ProductEntity): Product {
        return Product(
            productEntity.id,
            productEntity.categoryId,
            productEntity.name,
            productEntity.description,
            productEntity.image,
            productEntity.priceCurrent,
            productEntity.priceOld,
            productEntity.measure,
            productEntity.measureUnit,
            productEntity.energyPer100Grams,
            productEntity.proteinsPer100Grams,
            productEntity.fatsPer100Grams,
            productEntity.carbohydratesPer100Grams,
            productEntity.tagIds.map { it.digitToInt() },
            productEntity.count
        )
    }
}