package org.example.project.repo

import org.example.project.Photo
import org.example.project.model.Product
import org.example.project.model.ProductResponse

interface PhotoRepository {

    suspend fun getAllPhotoDetails(): List<Photo>
}


interface ProductRepository {
    suspend fun getAllProductDetails(): List<Product>
}
