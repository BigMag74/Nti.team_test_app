package com.example.ntiteamtestapp.di

import com.example.ntiteamtestapp.data.MainScreenRepositoryImpl
import com.example.ntiteamtestapp.domain.MainScreenRepository
import com.example.ntiteamtestapp.domain.ProductConverter
import com.example.ntiteamtestapp.domain.useCase.AddProductToDBUseCase
import com.example.ntiteamtestapp.domain.useCase.DeleteAllProductsFromDBUseCase
import com.example.ntiteamtestapp.domain.useCase.DeleteProductFromDBUseCase
import com.example.ntiteamtestapp.domain.useCase.impl.AddProductToDBUseCaseImpl
import com.example.ntiteamtestapp.domain.useCase.GetAllProductsUseCase
import com.example.ntiteamtestapp.domain.useCase.impl.GetAllProductsUseCaseImpl
import com.example.ntiteamtestapp.domain.useCase.GetCategoriesUseCase
import com.example.ntiteamtestapp.domain.useCase.GetProductsFromDBUseCase
import com.example.ntiteamtestapp.domain.useCase.impl.DeleteAllProductsFromDBUseCaseImpl
import com.example.ntiteamtestapp.domain.useCase.impl.DeleteProductFromDBUseCaseImpl
import com.example.ntiteamtestapp.domain.useCase.impl.GetCategoriesUseCaseImpl
import com.example.ntiteamtestapp.domain.useCase.impl.GetProductsFromDBUseCaseImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val mainScreenDomainModule = module {
    factoryOf(::ProductConverter)
    single<GetCategoriesUseCase> { GetCategoriesUseCaseImpl(get()) }
    single<GetAllProductsUseCase> { GetAllProductsUseCaseImpl(get()) }
    single<AddProductToDBUseCase> { AddProductToDBUseCaseImpl(get(), get()) }
    single<DeleteProductFromDBUseCase> { DeleteProductFromDBUseCaseImpl(get(), get()) }
    single<DeleteAllProductsFromDBUseCase> { DeleteAllProductsFromDBUseCaseImpl(get()) }
    single<GetProductsFromDBUseCase> { GetProductsFromDBUseCaseImpl(get(), get()) }
    single<MainScreenRepository> { MainScreenRepositoryImpl(get(), get()) }

}