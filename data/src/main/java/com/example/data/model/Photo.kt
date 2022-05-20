package com.example.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class ListOfPhotos(
    val photos : List<Photo>
)
@Entity(tableName = "photos_table")
data class Photo(

    @PrimaryKey(autoGenerate = true)
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
    val rover: Rover
)

data class Camera(
    val id: Int,
    val name: String,

    @SerializedName("rover_id")
    val roverID: Int,

    @SerializedName("full_name")
    val fullName: String
)

data class Rover(
    val id: Int,
    val name: String,

    @SerializedName("landing_date")
    val landingDate: String,

    @SerializedName("launch_date")
    val launchDate: String,

    val status: String
)
