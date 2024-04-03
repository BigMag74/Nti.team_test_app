package com.example.ntiteamtestapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ntiteamtestapp.R
import com.example.ntiteamtestapp.domain.model.Product
import com.example.ntiteamtestapp.presentation.composable.CategoryChips
import com.example.ntiteamtestapp.presentation.composable.GifImage
import com.example.ntiteamtestapp.presentation.MainScreenViewModel
import com.example.ntiteamtestapp.presentation.composable.ProductLazyColumn
import com.example.ntiteamtestapp.presentation.RootActivity.Companion.CART_SCREEN
import com.example.ntiteamtestapp.presentation.RootActivity.Companion.PRODUCT_INFORMATION_SCREEN
import com.example.ntiteamtestapp.presentation.theme.Orange

@Composable
fun MainScreen(viewModel: MainScreenViewModel, navController: NavController, products: MutableState<List<Product>>) {
    val categories = viewModel.categories.value
    val currentCategoryId = viewModel.firstCategoryId

    // Передается для скролинга наверх при смене категории
    val lazyGridState = LazyGridState()

    // Сумма в корзине
    val productsPriceCount = rememberSaveable {
        mutableIntStateOf(0)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = if (categories.isEmpty()) Orange else Color.White,
    ) {
        // Анимированный Splashscreen
        if (categories.isEmpty()) {
            GifImage()
        } else {
            // При переходе с другого экрана каждый раз пересчитываем общую сумму в корзине
            productsPriceCount.intValue =
                com.example.ntiteamtestapp.presentation.composable.culcProductPriceCount(products.value)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_logo
                    ),
                    contentDescription = "Logo",
                    modifier = Modifier.fillMaxWidth()
                )
                // Верхниче Чипсы с категероиями
                CategoryChips(categories = categories, lazyGridState) {
                    currentCategoryId.value = it
                }
                // LazyVerticalGrid с карточками товаров
                // Передаем только те товары которые приннадлежат данной категории
                ProductLazyColumn(
                    productsPriceCount = productsPriceCount,
                    products = getProductsByCategoryId(products.value, currentCategoryId.value),
                    state = lazyGridState,
                ) {
                    navController.navigate("$PRODUCT_INFORMATION_SCREEN/$it")
                }
            }
            // Кнопка корзины
            if (productsPriceCount.intValue > 0) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                           elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
                           colors = ButtonDefaults.buttonColors(Orange),
                           shape = RoundedCornerShape(8.dp),
                           contentPadding = PaddingValues(0.dp),
                           onClick = { navController.navigate(CART_SCREEN) }) {
                        Image(painter = painterResource(R.drawable.ic_cart), contentDescription = "Корзина")
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(text = (productsPriceCount.intValue / 100).toString() + " ₽")
                    }
                }
            }
        }
    }
}

private fun getProductsByCategoryId(products: List<Product>, categoryId: Int): List<Product> {
    val result: MutableList<Product> = mutableListOf()
    products.forEach {
        if (it.categoryId == categoryId)
            result.add(it)
    }
    return result
}