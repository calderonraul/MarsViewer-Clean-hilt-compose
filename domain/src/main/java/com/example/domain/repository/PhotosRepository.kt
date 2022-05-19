package com.example.domain.repository

import com.example.domain.entity.PhotoDomain

interface PhotosRepository {
    suspend fun getPhotos():List<PhotoDomain>
}