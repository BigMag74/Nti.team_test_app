package com.example.ntiteamtestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.example.ntiteamtestapp.ui.theme.NtiteamTestAppTheme

class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NtiteamTestAppTheme {
                // A surface container using the 'background' color from the theme
                val collectionTabs = categories
                val pagerPage = remember { mutableIntStateOf(0) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Image(painter = painterResource(
                            id = R.drawable.ic_logo),
                              contentDescription = "Logo",
                              modifier = Modifier.fillMaxWidth()
                        )
                        ScrollableTabRow(
                            indicator = {},
                            divider = {},
                            selectedTabIndex = minOf(collectionTabs.count(), pagerPage.intValue),
                            edgePadding = 0.dp,
                            containerColor = Color.Transparent,
                            modifier = Modifier.padding(8.dp),
                            tabs = {
                                collectionTabs.fastForEachIndexed { index, tab ->
                                    Tab(
                                        modifier = if (index == pagerPage.intValue) {
                                            Modifier.background(
                                                color = Color(getColor(R.color.orange)),
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                        } else {
                                            Modifier.background(Color.Transparent, shape = RoundedCornerShape(10.dp))
                                        },
                                        onClick = { pagerPage.intValue = index },
                                        text = { Text(tab) },
                                        selected = index == pagerPage.intValue,
                                        selectedContentColor = Color.White,
                                        unselectedContentColor = Color.Black
                                    )
                                }
                            }

                        )
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
