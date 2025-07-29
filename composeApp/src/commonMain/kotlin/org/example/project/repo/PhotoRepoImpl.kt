package org.example.project.repo

import org.example.project.Photo
import org.example.project.ktor.PostApi
import org.example.project.ktor.ProductApi
import org.example.project.model.Product
import org.example.project.model.ProductResponse

// Created by Zain Shakoor
// on 7/24/2025

class PhotoRepoImpl(private val postApi: PostApi) : PhotoRepository {
    override suspend fun getAllPhotoDetails(): List<Photo> {
        return postApi.getPostDetails()
    }
}


class ProductRepoImpl(private val productApi: ProductApi) : ProductRepository {
    override suspend fun getAllProductDetails(): List<Product> {
        return productApi.getProductDetails().products
    }

}

