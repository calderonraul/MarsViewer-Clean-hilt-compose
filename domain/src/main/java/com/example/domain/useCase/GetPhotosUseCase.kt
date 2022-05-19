package com.example.domain.useCase


import com.example.domain.repository.PhotosRepository
import com.example.utils.Resource
import retrofit2.HttpException

import kotlinx.coroutines.flow.Flow
import com.example.domain.entity.PhotoDomain
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetPhotosUseCase (private val repository: PhotosRepository){

    operator fun invoke():Flow<Resource<List<PhotoDomain>>> = flow {
        try {
            emit(Resource.Loading<List<PhotoDomain>>())
            val photos=repository.getPhotos()
            emit(Resource.Success<List<PhotoDomain>>(photos))
        }catch (e:HttpException){
            emit(Resource.Error<List<PhotoDomain>>(message = e.localizedMessage?:"Error Occurred"))

        }catch (e:IOException){
            emit(Resource.Error<List<PhotoDomain>>(e.localizedMessage?:"Network Error Occured"))
        }
    }

}