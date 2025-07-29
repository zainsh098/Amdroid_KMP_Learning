package org.example.project.state

import org.example.project.Photo
import org.example.project.model.Product

data class PhotoUiState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String? = null,
    val success: String? = null
)

data class ProductUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null,
    val success: String? = null
)
