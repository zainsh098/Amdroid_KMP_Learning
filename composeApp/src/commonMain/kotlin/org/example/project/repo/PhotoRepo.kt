package org.example.project.repo

import org.example.project.Photo

interface PhotoRepository {

    suspend fun getAllPhotoDetials(): List<Photo>
}