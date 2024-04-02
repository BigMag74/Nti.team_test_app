package com.example.ntiteamtestapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Delete
    suspend fun deleteProduct(productEntity: ProductEntity)

}