package com.example.domain.useCase


import com.example.domain.entity.ListOfPhotosDomain
import com.example.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow

class GetPhotosUseCase (private val repository: PhotosRepository){
     operator fun invoke():Flow<ListOfPhotosDomain>{
        return repository.getPhotos()
    }
}