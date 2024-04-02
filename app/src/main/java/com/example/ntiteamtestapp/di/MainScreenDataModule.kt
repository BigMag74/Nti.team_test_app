package com.example.ntiteamtestapp.di

import androidx.room.Room
import com.example.ntiteamtestapp.data.NetworkClient
import com.example.ntiteamtestapp.data.db.AppDatabase
import com.example.ntiteamtestapp.data.network.FoodiesApi
import com.example.ntiteamtestapp.data.network.FoodiesRetrofitNetworkClient
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainScreenDatamodule = module {
    single<FoodiesApi> {
        Retrofit.Builder()
            .baseUrl("https://anika1d.github.io/WorkTestServer/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodiesApi::class.java)
    }

    factory { Gson() }

    single<NetworkClient> { FoodiesRetrofitNetworkClient(get(), androidContext()) }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}