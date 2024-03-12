package com.example.productslistapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productslistapp.presentation.ScreensName.Companion.DATA_SCREEN
import com.example.productslistapp.presentation.ScreensName.Companion.PRODUCTS_SCREEN
import com.example.productslistapp.presentation.screens.ProductScreen
import com.example.productslistapp.presentation.screens.ProductsScreen
import com.example.productslistapp.presentation.viewmodel.ProductViewModel
import com.example.productslistapp.presentation.viewmodel.ProductViewModelFactory
import com.example.productslistapp.presentation.viewmodel.ProductsViewModel

class MainActivity : ComponentActivity() {

    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsViewModel = ViewModelProvider(
            this,
            ProductViewModelFactory()
        )[ProductsViewModel::class.java]
        productViewModel = ViewModelProvider(
            this,
            ProductViewModelFactory()
        )[ProductViewModel::class.java]
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = PRODUCTS_SCREEN
            ) {
                composable(PRODUCTS_SCREEN) {
                    ProductsScreen(productsViewModel = productsViewModel) {
                        navController.navigate("$DATA_SCREEN/$it")
                    }
                }
                composable("$DATA_SCREEN/{productId}") { id ->
                    val productId = id.arguments?.getString("productId")?.toIntOrNull() ?: 0
                    ProductScreen(productViewModel = productViewModel,
                        id = productId,
                        onClickNav = { navController.navigate(PRODUCTS_SCREEN) })
                }
            }
        }

    }
}

class ScreensName {
    companion object {
        const val PRODUCTS_SCREEN = "productsScreen"
        const val DATA_SCREEN = "dataScreen"
    }
}