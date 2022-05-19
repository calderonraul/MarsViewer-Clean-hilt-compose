package com.example.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Photo

@Dao
interface PhotosDao {
    @Query("SELECT * FROM photos_table")
    fun getAllPhotosRoom(): LiveData<List<Photo>>

    @Query("SELECT * FROM photos_table WHERE id= :id")
    fun getPhoto(id: Int): LiveData<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)
}
