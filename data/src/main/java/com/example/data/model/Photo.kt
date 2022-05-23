package com.example.data.model

import androidx.room.*
import com.google.gson.annotations.SerializedName




@Entity(tableName = "photos_table")
data class Photo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "sol")
    val sol: Int,
    @Embedded
    val camera: Camera,
    @ColumnInfo(name = "img_src")
    @SerializedName("img_src")
    val imgSrc: String,
    @ColumnInfo(name = "earth_date")
    @SerializedName("earth_date")
    val earthDate: String,
    @Embedded
    val rover: Rover,
    val photos: List<Photo>
)


data class Camera(
    @SerializedName("id")
    val idCamera: Int,
    @SerializedName("name")
    val nameCamera: String,
    @SerializedName("rover_id")
    val roverID: Int,
    @SerializedName("full_name")
    val fullName: String
)

data class Rover(
    @SerializedName("id")
    val idRover: Int,
    @SerializedName("name")
    val nameRover: String,
    @SerializedName("landing_date")
    val landingDate: String,
    @SerializedName("launch_date")
    val launchDate: String,
    val status: String
)
