package com.example.domain.repository

import com.example.domain.entity.ListOfPhotosDomain
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
      fun getPhotos(): Flow<ListOfPhotosDomain>
}