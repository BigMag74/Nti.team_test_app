package com.example.ntiteamtestapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ntiteamtestapp.R
import com.example.ntiteamtestapp.domain.model.Product
import com.example.ntiteamtestapp.presentation.theme.Orange
import androidx.compose.ui.unit.sp
import com.example.ntiteamtestapp.presentation.RootActivity
import com.example.ntiteamtestapp.presentation.theme.GrayBg
import com.example.ntiteamtestapp.presentation.theme.GrayText

@Composable
fun ProductInformationScreen(
    navController: NavController,
    products: MutableState<List<Product>>,
    productId: Int) {
    val product: Product = findProductById(products.value, productId)!!

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Кнопка назад
        Button(modifier = Modifier
            .padding(8.dp)
            .size(48.dp),
               contentPadding = PaddingValues(0.dp),
               colors = ButtonDefaults.buttonColors(Color.White),
               elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
               onClick = { navController.navigateUp() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left), contentDescription = "Кнопка назад",
                tint = Color.Black
            )
        }
        // Фото
        Image(
            painter = painterResource(id = R.drawable.placeholder_product),
            contentDescription = "Продукт",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        // Название
        Text(text = product.name, fontSize = 34.sp, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
        // Описание
        Text(
            text = product.description, color = GrayText,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
        )
        // Разделитель
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .background(GrayBg)
                .fillMaxWidth()
        )
        // Вес
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Вес", color = GrayText)
            Text(text = "${product.measure} ${product.measureUnit}")
        }
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .background(GrayBg)
                .fillMaxWidth()
        )
        // Энергетическая ценность
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Энергетическая ценность", color = GrayText)
            Text(text = "${product.energyPer100Grams}")
        }
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .background(GrayBg)
                .fillMaxWidth()
        )
        // Белки
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Белки", color = GrayText)
            Text(text = "${product.proteinsPer100Grams}")
        }
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .background(GrayBg)
                .fillMaxWidth()
        )
        // Жиры
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Жиры", color = GrayText)
            Text(text = "${product.fatsPer100Grams}")
        }
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .background(GrayBg)
                .fillMaxWidth()
        )
        // Углеводы
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Углеводы", color = GrayText)
            Text(text = "${product.carbohydratesPer100Grams}")
        }
        Spacer(
            modifier = Modifier
                .height(2.dp)
                .background(GrayBg)
                .fillMaxWidth()
        )

        // Кнопка перехода в корзину
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
               elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
               colors = ButtonDefaults.buttonColors(Orange),
               shape = RoundedCornerShape(8.dp),
               contentPadding = PaddingValues(0.dp),
               onClick = {
                   product.productsInCart++
                   navController.navigate(RootActivity.CART_SCREEN)
               }) {
            Text(text = ("В корзину за ${product.priceCurrent.div(100)} ₽"))
        }
    }
}

private fun findProductById(products: List<Product>, id: Int): Product? {
    products.forEach { if (it.id == id) return it }
    return null
}