package com.example.domain.repository

import com.example.domain.entity.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
      suspend fun getPhotos()
      fun getDataFromRoom(): Flow<List<PhotoDomain>>
}