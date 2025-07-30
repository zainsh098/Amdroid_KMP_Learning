package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.viewmodel.ProductViewModel
import org.example.project.viewmodel.SharedViewModel
import org.koin.androidx.compose.koinViewModel

// Created by Zain Shakoor
// on 7/28/2025


@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = koinViewModel()
    val productViewModel: ProductViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = "productList"
    ) {
        composable("productList") {
            RelatedProductScreen(
                navController, productViewModel = productViewModel,
                sharedProductViewModel = sharedViewModel
            )
        }

        composable("productDetails") {
            ProductDetailScreen(sharedViewModel) // <- This is the screen it navigates to
        }
    }
}


