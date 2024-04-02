package com.example.ntiteamtestapp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ntiteamtestapp.R
import com.example.ntiteamtestapp.domain.model.ProductCard
import com.example.ntiteamtestapp.presentation.theme.NtiteamTestAppTheme
import com.example.ntiteamtestapp.presentation.theme.Orange

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NtiteamTestAppTheme {
                val productsPriceCount = remember {
                    mutableIntStateOf(0)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_logo
                            ),
                            contentDescription = "Logo",
                            modifier = Modifier.fillMaxWidth()
                        )
                        CategoryChips(categories = categories) {
                            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                        }
                        ProductLazyColumn(productsPriceCount = productsPriceCount)
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        if (productsPriceCount.intValue > 0) {
                            Button(modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                                   elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
                                   colors = ButtonDefaults.buttonColors(Orange),
                                   shape = RoundedCornerShape(8.dp),
                                   contentPadding = PaddingValues(0.dp),
                                   onClick = { /*TODO*/ }) {
                                Image(painter = painterResource(R.drawable.ic_cart), contentDescription = "Корзина")
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text(text = productsPriceCount.intValue.toString() + " ₽")
                            }
                        }
                    }
                }

            }
        }
    }
}

val product = ProductCard(2, 0, "Название блюда", "1.png", 500, "г", 500, 800, listOf(1, 2, 4))

val products = listOf(
    product, product.copy(priceOld = null, tagIds = listOf(4)), product.copy(tagIds = listOf()), product, product
)

val categories = listOf(
    "Роллы",
    "Сяки мяки",
    "Бургеры",
    "Свинина",
    "Говядина",
    "Филадельфия",
    "Еще что то"
)
