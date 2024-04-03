package com.example.ntiteamtestapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ntiteamtestapp.R
import com.example.ntiteamtestapp.domain.model.Product
import com.example.ntiteamtestapp.presentation.composable.BottomShadow
import com.example.ntiteamtestapp.presentation.theme.GrayBg
import com.example.ntiteamtestapp.presentation.theme.GrayText
import com.example.ntiteamtestapp.presentation.theme.Orange

@Composable
fun CartScreen(
    navController: NavController,
    products: MutableState<List<Product>>,
) {
    val cartProducts = getCartProducts(products.value)
    val productsPriceCount = rememberSaveable {
        mutableIntStateOf(culcProductPriceCount(cartProducts))
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
        ) {
            // Кнопка назад
            Icon(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { navController.navigateUp() },
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "Кнопка назад",
                tint = Orange
            )
            Text(
                modifier = Modifier.padding(start = 32.dp),
                text = "Корзина", fontSize = 18.sp
            )
        }

        // Тень под заголовком
        BottomShadow(alpha = .15f, height = 8.dp)

        // Состояние "Корзина пуста"
        if (productsPriceCount.intValue <= 0) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    modifier = Modifier.size(96.dp),
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = "Корзина",
                    tint = Orange
                )
                Text(text = "Корзина пуста")
            }
            // Если не пуста
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(cartProducts) { product ->
                    val productCount = rememberSaveable {
                        mutableIntStateOf(product.productsInCart)
                    }
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Row(modifier = Modifier.background(Color.White)) {
                            // Фото
                            Image(
                                modifier = Modifier
                                    .size(96.dp)
                                    .padding(4.dp),
                                painter = painterResource(id = R.drawable.placeholder_product),
                                contentDescription = "Продукт"
                            )
                            Column(
                                modifier = Modifier
                                    .height(96.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                // Название
                                Text(
                                    modifier = Modifier.padding(top = 4.dp),
                                    text = product.name
                                )
                                // Кнопки управления количеством товара
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(modifier = Modifier
                                        .size(40.dp)
                                        .padding(bottom = 16.dp)
                                        .aspectRatio(1f),
                                           contentPadding = PaddingValues(8.dp),
                                           colors = ButtonDefaults.buttonColors(GrayBg),
                                           shape = RoundedCornerShape(8.dp),
                                           onClick = {
                                               product.productsInCart--
                                               productCount.intValue--
                                               productsPriceCount.intValue -= product.priceCurrent
                                           }) {
                                        Image(
                                            painter = painterResource(R.drawable.ic_minus),
                                            contentDescription = "Минус"
                                        )

                                    }
                                    Text(
                                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                                        text = productCount.intValue.toString()
                                    )
                                    Button(modifier = Modifier
                                        .size(40.dp)
                                        .padding(bottom = 16.dp)
                                        .aspectRatio(1f),
                                           contentPadding = PaddingValues(8.dp),
                                           colors = ButtonDefaults.buttonColors(GrayBg),
                                           shape = RoundedCornerShape(8.dp),
                                           onClick = {
                                               product.productsInCart++
                                               productCount.intValue++
                                               productsPriceCount.intValue += product.priceCurrent
                                           }) {
                                        Image(
                                            painter = painterResource(R.drawable.ic_plus),
                                            contentDescription = "Плюс"
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(96.dp)
                                .padding(bottom = 8.dp, end = 4.dp),
                            verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End
                        ) {
                            // Цена товара
                            Text(text = "${product.priceCurrent / 100} ₽")
                            // Старая цена при наличии
                            if (product.priceOld != null) {
                                Text(
                                    textDecoration = TextDecoration.LineThrough,
                                    text = (product.priceOld.div(100)).toString() + " ₽",
                                    color = GrayText
                                )
                            }
                        }

                    }

                    // Разделитель
                    Spacer(
                        modifier = Modifier
                            .height(2.dp)
                            .background(GrayBg)
                            .fillMaxWidth()
                    )
                }
                // Кнопка Закзать
                if (productsPriceCount.intValue > 0) {
                    item {
                        Button(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                               elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
                               colors = ButtonDefaults.buttonColors(Orange),
                               shape = RoundedCornerShape(8.dp),
                               contentPadding = PaddingValues(0.dp),
                               onClick = { /*TODO*/ }) {
                            Text(text = "Заказать за ${productsPriceCount.intValue / 100} ₽")
                        }
                    }
                }
            }
        }
    }
}

// Получение общей ссумы корзины
private fun culcProductPriceCount(products: List<Product>): Int {
    var res = 0
    products.forEach { res += it.priceCurrent * it.productsInCart }
    return res
}

// Получение списка товаров в корзине
private fun getCartProducts(products: List<Product>): List<Product> {
    val res: MutableList<Product> = mutableListOf()
    products.forEach {
        if (it.productsInCart > 0)
            res.add(it)
    }
    return res
}