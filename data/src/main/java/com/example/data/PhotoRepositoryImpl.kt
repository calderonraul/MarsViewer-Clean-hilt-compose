package com.example.data

import com.example.data.api.PhotosApi
import com.example.data.model.ListOfPhotosMapper
import com.example.domain.entity.ListOfPhotosDomain
import com.example.domain.repository.PhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class PhotoRepositoryImpl(private val api: PhotosApi, private val mapper: ListOfPhotosMapper) :
    PhotosRepository {
    override fun getPhotos(): Flow<ListOfPhotosDomain> = flow {
        val listAux =
            mapper.mapFromEntity(api.getAllPhotos(1000, "dDZHG9j3IUJCxEJT0JBxQImW8ffoK1DZdaq9lXXe"))
        emit(listAux)
    }.flowOn(Dispatchers.IO)
}