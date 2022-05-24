package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos_table")
    fun getAllPhotosRoom(): Flow<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Query("SELECT * FROM photos_table WHERE nameCamera= :name")
    fun getPhotoByCameraName(name:String): Flow<List<Photo>>

    @Query("DELETE FROM photos_table")
    fun clearTable()

}
