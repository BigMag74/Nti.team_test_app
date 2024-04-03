package com.example.ntiteamtestapp.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ntiteamtestapp.R
import com.example.ntiteamtestapp.domain.model.Product
import com.example.ntiteamtestapp.presentation.theme.GrayBg
import com.example.ntiteamtestapp.presentation.theme.GrayText

@Composable
fun ProductLazyColumn(productsPriceCount: MutableIntState,
                      products: List<Product>,
                      state: LazyGridState,
    // Передаем id продукта для пробрасывания его на экран "карточка товара"
                      onClick: (Int) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.padding(PaddingValues(horizontal = 4.dp)),
        state = state,
        columns = GridCells.Fixed(2)
    ) {
        items(products) { product ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(GrayBg)
                    .clickable { onClick(product.id) }
            ) {
                // Просталвяем теги
                if (product.tagIds.isNotEmpty()) {
                    LazyRow {
                        items(product.tagIds) { tag ->
                            when (tag) {
                                1, 3, 5 -> {
                                    Image(
                                        modifier = Modifier.padding(
                                            PaddingValues(start = 4.dp, top = 4.dp)
                                        ),
                                        painter = painterResource(R.drawable.ic_discount),
                                        contentDescription = "Новинка"
                                    )
                                }

                                2 -> {
                                    Image(
                                        modifier = Modifier.padding(
                                            PaddingValues(start = 4.dp, top = 4.dp)
                                        ),
                                        painter = painterResource(R.drawable.ic_vegan),
                                        contentDescription = "Вегетарианское блюдо"
                                    )
                                }

                                4 -> {
                                    Image(
                                        modifier = Modifier.padding(
                                            PaddingValues(start = 4.dp, top = 4.dp)
                                        ),
                                        painter = painterResource(R.drawable.ic_spicy),
                                        contentDescription = "Острое"
                                    )
                                }
                            }
                        }
                    }
                }

                Column {
                    // Фото товара. Пока с сервера ничего не приходит, поэтому поставил плейсхолдер
                    Image(
                        modifier = Modifier.aspectRatio(1f),
                        painter = painterResource(id = R.drawable.placeholder_product),
                        contentDescription = "Product image"
                    )
                    // Название
                    Text(
                        text = product.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(PaddingValues(horizontal = 8.dp))
                    )
                    // Грамовка
                    Row(modifier = Modifier.padding(PaddingValues(horizontal = 8.dp))) {
                        Text(text = product.measure.toString(), color = GrayText)
                        Text(text = " " + product.measureUnit, color = GrayText)
                    }
                    //Кнопка добавить с ценой товара
                    if (product.productsInCart == 0) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(PaddingValues(horizontal = 8.dp, vertical = 4.dp)),
                            elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
                            colors = ButtonDefaults.buttonColors(Color.White),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(0.dp),

                            onClick = {
                                productsPriceCount.intValue += product.priceCurrent
                                product.productsInCart++
                            },
                        ) {
                            // Цена
                            Row {
                                Text(
                                    text = (product.priceCurrent / 100).toString() + " ₽",
                                    color = Color.Black
                                )
                                // Проставляем старую цену если она есть
                                if (product.priceOld != null) {
                                    Spacer(modifier = Modifier.padding(4.dp))
                                    Text(
                                        textDecoration = TextDecoration.LineThrough,
                                        text = (product.priceOld.div(100)).toString() + " ₽",
                                        color = GrayText
                                    )
                                }
                            }
                        }
                        // Кнопки регулирование количества товара в корзине
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(modifier = Modifier
                                .size(40.dp)
                                .aspectRatio(1f),
                                   contentPadding = PaddingValues(8.dp),
                                   elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
                                   colors = ButtonDefaults.buttonColors(Color.White),
                                   shape = RoundedCornerShape(8.dp),
                                   onClick = {
                                       product.productsInCart--
                                       productsPriceCount.intValue -= product.priceCurrent
                                   }) {
                                Image(
                                    painter = painterResource(R.drawable.ic_minus),
                                    contentDescription = "Минус"
                                )

                            }
                            Text(text = product.productsInCart.toString())
                            Button(modifier = Modifier
                                .size(40.dp)
                                .aspectRatio(1f),
                                   contentPadding = PaddingValues(8.dp),
                                   elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
                                   colors = ButtonDefaults.buttonColors(Color.White),
                                   shape = RoundedCornerShape(8.dp),
                                   onClick = {
                                       product.productsInCart++
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
            }
        }
        // пустое пространство для того, чтобы кнопка корзины не перекрывала товары
        item { Spacer(modifier = Modifier.height(56.dp)) }
        item { Spacer(modifier = Modifier.height(56.dp)) }
    }
}

fun culcProductPriceCount(products: List<Product>): Int {
    var res = 0
    products.forEach { res += it.priceCurrent * it.productsInCart }
    return res
}