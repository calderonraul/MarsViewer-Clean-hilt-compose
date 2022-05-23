package com.example.domain.entity



data class PhotoDomain(
    val id: Int,
    val sol: Int,
    val camera: CameraDomain,
    val imgSrc: String,
    val earthDate: String,
    val rover: RoverDomain,
    val list : List<PhotoDomain>
)

data class CameraDomain(
    val cameraId: Int,
    val cameraName: String,
    val roverID: Int,
    val fullName: String
)

data class RoverDomain(
    val roverId: Int,
    val roverName: String,
    val landingDate: String,
    val launchDate: String,
    val status: String
)
