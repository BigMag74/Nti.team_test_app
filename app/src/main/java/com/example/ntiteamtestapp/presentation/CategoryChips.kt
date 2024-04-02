package com.example.ntiteamtestapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.example.ntiteamtestapp.presentation.theme.Orange

@Composable
fun CategoryChips(
    categories: List<String>,
    onCategoryClick: (String) -> Unit,
) {
    val collectionTabs = categories
    val pagerPage = remember { mutableIntStateOf(0) }

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
                            color = Orange,
                            shape = RoundedCornerShape(8.dp)
                        )
                    } else {
                        Modifier.background(Color.Transparent, shape = RoundedCornerShape(8.dp))
                    },
                    onClick = { pagerPage.intValue = index
                              onCategoryClick(tab)},
                    text = { Text(tab) },
                    selected = index == pagerPage.intValue,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Black
                )
            }
        }

    )
}