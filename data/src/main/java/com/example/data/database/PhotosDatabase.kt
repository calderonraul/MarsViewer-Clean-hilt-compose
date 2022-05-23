package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.model.Photo


@Database(entities = [Photo::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PhotosDatabase : RoomDatabase() {
    abstract val photosDao: PhotosDao
}