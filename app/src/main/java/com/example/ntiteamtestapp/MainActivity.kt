package com.example.ntiteamtestapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.ntiteamtestapp.ui.theme.NtiteamTestAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NtiteamTestAppTheme {

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
                        CategoryChips(categories = categories, context = this@MainActivity) {
                            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                        }

                    }

                }

            }
        }
    }
}

val categories = listOf(
    "Роллы",
    "Сяки мяки",
    "Бургеры",
    "Свинина",
    "Говядина",
    "Филадельфия",
    "Еще что то"
)
