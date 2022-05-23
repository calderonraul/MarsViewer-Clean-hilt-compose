package com.example.domain.useCase


import com.example.domain.entity.PhotoDomain
import com.example.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow

class GetPhotosUseCase (private val repository: PhotosRepository){
      operator fun invoke(): Flow<List<PhotoDomain>> {
        return repository.getDataFromRoom()
    }

    suspend fun initDB(){
        repository.getPhotos()
    }
}