package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.Photo


@Database(
    entities = [Photo::class], version = 1, exportSchema = false
)


abstract class PhotosDatabase:RoomDatabase() {
    abstract val photosDao:PhotosDao
}