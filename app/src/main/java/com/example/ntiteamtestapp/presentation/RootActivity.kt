package com.example.ntiteamtestapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ntiteamtestapp.presentation.theme.NtiteamTestAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootActivity : ComponentActivity() {

    private val viewModel by viewModel<MainScreenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NtiteamTestAppTheme {
                NavHost(navController = navController, startDestination = MAIN_SCREEN) {
                    composable(MAIN_SCREEN) {
                        MainScreen(viewModel = viewModel, navController)
                    }
                    composable("$PRODUCT_INFORMATION_SCREEN/{productId}",
                               arguments = listOf(
                                   navArgument(name = "productId") {
                                       type = NavType.IntType
                                   }
                               )) {
                        ProductInformationScreen(it.arguments?.getInt("productId"))
                    }
                    composable(CART_SCREEN) {
                        CartScreen()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.deleteAllProductsFromDBUseCase()
    }

    companion object {

        const val MAIN_SCREEN = "main screen"
        const val PRODUCT_INFORMATION_SCREEN = "product information screen"
        const val CART_SCREEN = "cart screen"
    }
}