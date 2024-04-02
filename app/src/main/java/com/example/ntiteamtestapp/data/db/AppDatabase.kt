package com.example.ntiteamtestapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [ProductEntity::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
}