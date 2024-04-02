package com.example.ntiteamtestapp.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ntiteamtestapp.domain.model.Category
import com.example.ntiteamtestapp.domain.useCase.GetCategoriesUseCase
import kotlinx.coroutines.launch

class MainScreenViewModel(getCategoriesUseCase: GetCategoriesUseCase) : ViewModel() {

    val categories: MutableState<List<Category>> = mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            getCategoriesUseCase.execute().collect {
                when {
                    it.first == null -> {}
                    it.second == null -> categories.value = it.first!!
                }
            }
        }
    }
}