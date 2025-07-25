package org.example.project.repo

import org.example.project.Photo
import org.example.project.ktor.PostApi

// Created by Zain Shakoor
// on 7/24/2025

class PhotoRepoImpl(private val postApi: PostApi) : PhotoRepository {
    override suspend fun getAllPhotoDetials(): List<Photo> {
        return postApi.getPostDetails()
    }


}