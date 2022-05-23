package com.example.data.database

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.example.data.model.Photo
import com.google.gson.Gson


class Converters() {

    @TypeConverter
    fun toPhotoJson(photos: List<Photo>?): String {
        return Gson().toJson(
            photos,
            object : TypeToken<List<Photo>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromPhotosJson(json: String): List<Photo> {
        return Gson().fromJson<List<Photo>>(
            json,
            object : TypeToken<List<Photo>>() {}.type
        ) ?: emptyList()
    }
}