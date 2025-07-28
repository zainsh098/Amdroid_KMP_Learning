package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Created by Zain Shakoor
// on 7/28/2025


@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "productList"
    ) {
        composable("productList") {
            RelatedProductScreen(navController)
        }

        composable("productDetails") {
            ProductDetailScreen() // <- This is the screen it navigates to
        }
    }
}


