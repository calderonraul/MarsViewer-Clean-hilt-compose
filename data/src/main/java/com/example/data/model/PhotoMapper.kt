package com.example.data.model

import com.example.data.mappers.EntityMapper
import com.example.domain.entity.PhotoDomain

class PhotoMapper : EntityMapper<Photo, PhotoDomain> {
    override fun mapFromEntity(entity: Photo): PhotoDomain {
        return PhotoDomain(
            id = entity.id,
            sol = entity.sol,
            camera = entity.camera.let { CameraMapper().mapFromEntity(it) },
            rover = entity.rover.let { RoverMapper().mapFromEntity(it) },
            earthDate = entity.earthDate,
            imgSrc = entity.imgSrc,
            list = fromEntityList(entity.photos)
        )
    }

    override fun mapToEntity(domainModel: PhotoDomain): Photo {
        return Photo(
            id = domainModel.id,
            sol = domainModel.sol,
            camera = domainModel.camera.let { CameraMapper().mapToEntity(it) },
            rover = domainModel.rover.let { RoverMapper().mapToEntity(it) },
            earthDate = domainModel.earthDate,
            imgSrc = domainModel.imgSrc,
            photos = toEntityList(domainModel.list)
        )
    }

    fun fromEntityList(initial: List<Photo>): List<PhotoDomain> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<PhotoDomain>): List<Photo> {
        return initial.map { mapToEntity(it) }
    }

}