package com.example.ntiteamtestapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Delete
    suspend fun deleteProduct(productEntity: ProductEntity)

    @Query("DELETE FROM productentity")
    suspend fun deleteAll()

    @Query("SELECT * FROM productentity WHERE id = :productId")
    suspend fun getProductById(productId: Int): ProductEntity

    @Query("SELECT * FROM productentity")
    fun getAllProducts(): Flow<List<ProductEntity>>
}