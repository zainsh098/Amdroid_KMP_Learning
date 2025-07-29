package org.example.project.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val products: List<Product>
)
@Serializable
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags: List<String>,
    val brand: String? = null, // 🔧 Make nullable
    val sku: String? = null,
    val weight: Int? = null,
    val dimensions: Dimensions? = null,
    val warrantyInformation: String? = null,
    val shippingInformation: String? = null,
    val availabilityStatus: String? = null,
    val reviews: List<Review>? = null,
    val returnPolicy: String? = null,
    val minimumOrderQuantity: Int? = null,
    val meta: Meta? = null,
    val images: List<String>? = null,
    val thumbnail: String? = null
)


@Serializable
data class Dimensions(
    val width: Double,
    val height: Double,
    val depth: Double
)

@Serializable
data class Review(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)

@Serializable
data class Meta(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String
)

