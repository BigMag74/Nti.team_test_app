package com.example.ntiteamtestapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.ntiteamtestapp.R
import com.example.ntiteamtestapp.presentation.theme.GrayBg

@Composable
fun ProductLazyColumn(productsPriceCount: MutableIntState) {
    LazyVerticalGrid(
        modifier = Modifier.padding(PaddingValues(horizontal = 4.dp)),
        columns = GridCells.Fixed(2)
    ) {
        items(products) { product ->
            val productCount = rememberSaveable {
                mutableIntStateOf(0)
            }
            val oldPrice = remember {
                mutableStateOf(product.priceOld)
            }

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(GrayBg)
            ) {
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
                    Image(
                        modifier = Modifier.aspectRatio(1f),
                        painter = painterResource(id = R.drawable.placeholder_product),
                        contentDescription = "Product image"
                    )
                    Text(
                        text = product.name,
                        modifier = Modifier.padding(PaddingValues(horizontal = 8.dp))
                    )
                    Row(modifier = Modifier.padding(PaddingValues(horizontal = 8.dp))) {
                        Text(text = product.measure.toString())
                        Text(text = " " + product.measureUnit)
                    }
                    if (productCount.intValue == 0) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(PaddingValues(horizontal = 8.dp, vertical = 4.dp)),
                            elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
                            colors = ButtonDefaults.buttonColors(Color.White),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(0.dp),

                            onClick = {
                                productCount.intValue++
                                productsPriceCount.intValue += product.priceCurrent
                            },
                        ) {
                            if (oldPrice.value == null) {
                                Text(
                                    text = product.priceCurrent.toString() + " ₽",
                                    color = Color.Black
                                )
                            } else {
                                Row {
                                    Text(
                                        text = product.priceCurrent.toString() + " ₽",
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.padding(4.dp))
                                    Text(
                                        textDecoration = TextDecoration.LineThrough,
                                        text = product.priceOld.toString() + " ₽",
                                        color = Color.Black
                                    )
                                }
                            }

                        }
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
                                       productCount.intValue--
                                       productsPriceCount.intValue -= product.priceCurrent
                                   }) {
                                Image(
                                    painter = painterResource(R.drawable.ic_minus),
                                    contentDescription = "Минус"
                                )

                            }
                            Text(text = productCount.intValue.toString())
                            Button(modifier = Modifier
                                .size(40.dp)
                                .aspectRatio(1f),
                                   contentPadding = PaddingValues(8.dp),
                                   elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
                                   colors = ButtonDefaults.buttonColors(Color.White),
                                   shape = RoundedCornerShape(8.dp),
                                   onClick = {
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
            }
        }
    }
}