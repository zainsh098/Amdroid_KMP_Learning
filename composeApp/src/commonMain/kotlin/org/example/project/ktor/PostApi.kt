package org.example.project.ktor

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.project.Photo

// Created by Zain Shakoor
// on 7/24/2025

class PostApi(private val client: HttpClient) {
    suspend fun getPostDetails(): List<Photo> {
        return client.get("https://jsonplaceholder.typicode.com/photos").body()
    }
}