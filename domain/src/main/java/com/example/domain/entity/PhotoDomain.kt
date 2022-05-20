package com.example.domain.entity



data class ListOfPhotosDomain(

    val list : List<PhotoDomain>
)
data class PhotoDomain(
    val id: Int,
    val sol: Int,
    val camera: CameraDomain,
    val imgSrc: String,
    val earthDate: String,
    val rover: RoverDomain
)

data class CameraDomain(
    val id: Int,
    val name: String,
    val roverID: Int,
    val fullName: String
)

data class RoverDomain(
    val id: Int,
    val name: String,
    val landingDate: String,
    val launchDate: String,
    val status: String
)
