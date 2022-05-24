package com.example.data

import com.example.data.api.PhotosApi
import com.example.data.database.PhotosDao
import com.example.data.model.PhotoMapper
import com.example.domain.entity.PhotoDomain
import com.example.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PhotoRepositoryImpl(
    private val api: PhotosApi,
    private val mapper: PhotoMapper,
    private val dao: PhotosDao
) :
    PhotosRepository {
    override suspend fun getPhotos() {
        val listAux =
            api.getAllPhotos(1000, "dDZHG9j3IUJCxEJT0JBxQImW8ffoK1DZdaq9lXXe")
        dao.clearTable()
        listAux.photos?.let {
            dao.insertAll(it)
        }
    }


    override fun getDataFromRoom(name: String): Flow<List<PhotoDomain>> {
        if(name.isBlank()){
            return  dao.getAllPhotosRoom().map {
                mapper.fromEntityList(it)
            }
        }else{
            return dao.getPhotoByCameraName(name).map {
                mapper.fromEntityList(it)
            }
        }
    }


}




