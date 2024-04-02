package com.example.ntiteamtestapp.di

import com.example.ntiteamtestapp.presentation.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenPresentationModule = module {
    viewModel {
        MainScreenViewModel(get(), get(), get(), get(), get())
    }
}