package com.example.ntiteamtestapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ntiteamtestapp.presentation.screens.CartScreen
import com.example.ntiteamtestapp.presentation.screens.MainScreen
import com.example.ntiteamtestapp.presentation.screens.ProductInformationScreen
import com.example.ntiteamtestapp.presentation.theme.NtiteamTestAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : ComponentActivity() {

    private val viewModel by viewModel<MainScreenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Общий список продуктов, который будет передаваться в каждый экран
        val products = viewModel.products
        setContent {
            val navController = rememberNavController()
            NtiteamTestAppTheme {
                // NavGraph
                NavHost(navController = navController, startDestination = MAIN_SCREEN) {
                    composable(MAIN_SCREEN) {
                        MainScreen(viewModel = viewModel, navController, products)
                    }
                    // Передача productId в экран "Карточка товара"
                    composable("$PRODUCT_INFORMATION_SCREEN/{productId}",
                               arguments = listOf(
                                   navArgument(name = "productId") {
                                       type = NavType.IntType
                                   }
                               )) {
                        it.arguments?.getInt("productId")?.let { it1 ->
                            ProductInformationScreen(
                                navController = navController,
                                productId = it1,
                                products = products
                            )
                        }
                    }
                    composable(CART_SCREEN) {
                        CartScreen(
                            navController = navController,
                            products = products
                        )
                    }
                }
            }
        }
    }

    companion object {

        const val MAIN_SCREEN = "main screen"
        const val PRODUCT_INFORMATION_SCREEN = "product information screen"
        const val CART_SCREEN = "cart screen"
    }
}