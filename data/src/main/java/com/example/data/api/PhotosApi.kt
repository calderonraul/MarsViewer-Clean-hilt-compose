package com.example.data.api

import com.example.data.model.ApiResponse
import com.example.data.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApi {

    @GET("api/v1/rovers/curiosity/photos")
    suspend fun getAllPhotos(@Query("sol") sol:Int,@Query("api_key") apiKey:String) : ApiResponse<Photo>

    @GET("api/v1/rovers/curiosity/photos")
    suspend fun getCameraPhotos(@Query("sol") sol:Int,@Query("api_key") apiKey:String,@Query("camera")name:String) : ApiResponse<Photo>
}