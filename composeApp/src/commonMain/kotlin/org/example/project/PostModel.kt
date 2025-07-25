package org.example.project

import kotlinx.serialization.Serializable


//
//data class PostModel(
//    val title: String,
//    val id: String,
//    val albumId: String,
//    val resource: DrawableResource)
@Serializable
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)