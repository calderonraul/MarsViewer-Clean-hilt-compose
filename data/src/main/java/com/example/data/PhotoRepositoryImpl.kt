package com.example.data

import com.example.data.api.PhotosApi
import com.example.data.model.Photo
import com.example.data.model.PhotoMapper
import com.example.domain.entity.PhotoDomain
import com.example.domain.repository.PhotosRepository


class PhotoRepositoryImpl (private val api: PhotosApi): PhotosRepository {
    override suspend fun getPhotos(): List<PhotoDomain> {
        return PhotoMapper().fromEntityList(api.getAllPhotos(1000,"dDZHG9j3IUJCxEJT0JBxQImW8ffoK1DZdaq9lXXe"))
    }
}