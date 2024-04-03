package com.example.ntiteamtestapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.example.ntiteamtestapp.domain.model.Category
import com.example.ntiteamtestapp.presentation.theme.Orange

@Composable
fun CategoryChips(
    categories: List<Category>,
    lazyGridState: LazyGridState,
    onCategoryClick: (Int) -> Unit,
) {
    val pagerPage = rememberSaveable { mutableIntStateOf(0) }
    val isClicked = remember { mutableStateOf(false) }

    ScrollableTabRow(
        indicator = {},
        divider = {},
        selectedTabIndex = minOf(categories.count(), pagerPage.intValue),
        edgePadding = 0.dp,
        containerColor = Color.Transparent,
        modifier = Modifier.padding(8.dp),
        tabs = {
            categories.fastForEachIndexed { index, tab ->
                Tab(
                    modifier = if (index == pagerPage.intValue) {
                        Modifier.background(
                            color = Orange,
                            shape = RoundedCornerShape(8.dp)
                        )
                    } else {
                        Modifier.background(Color.Transparent, shape = RoundedCornerShape(8.dp))
                    },
                    onClick = {
                        pagerPage.intValue = index
                        onCategoryClick(tab.id)
                        isClicked.value = true
                    },
                    text = { Text(tab.name) },
                    selected = index == pagerPage.intValue,
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Black
                )
            }
            if (isClicked.value) {
                LaunchedEffect(true) {
                    lazyGridState.scrollToItem(0)
                    isClicked.value = false
                }
            }
        }

    )
}