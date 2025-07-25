package org.example.project.state

import org.example.project.Photo

data class PhotoUiState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String? = null,
    val success: String? = null)
