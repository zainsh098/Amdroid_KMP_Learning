package org.example.project.intent

// Created by Zain Shakoor
// on 7/24/2025

sealed class PhotoIntent {
    data object loadPhotos : PhotoIntent()
}


sealed class ProductIntent {
    data object LoadProductandDetails : ProductIntent()
//    data object loadProductsDetails : ProductIntent()
}