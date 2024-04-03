package com.example.ntiteamtestapp.di

import com.example.ntiteamtestapp.data.MainScreenRepositoryImpl
import com.example.ntiteamtestapp.domain.MainScreenRepository
import com.example.ntiteamtestapp.domain.useCase.GetAllProductsUseCase
import com.example.ntiteamtestapp.domain.useCase.impl.GetAllProductsUseCaseImpl
import com.example.ntiteamtestapp.domain.useCase.GetCategoriesUseCase
import com.example.ntiteamtestapp.domain.useCase.impl.GetCategoriesUseCaseImpl

import org.koin.dsl.module

val mainScreenDomainModule = module {
    single<GetCategoriesUseCase> { GetCategoriesUseCaseImpl(get()) }
    single<GetAllProductsUseCase> { GetAllProductsUseCaseImpl(get()) }
    single<MainScreenRepository> { MainScreenRepositoryImpl(get()) }

}