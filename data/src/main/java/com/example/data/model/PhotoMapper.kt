package com.example.data.model

import com.example.data.mappers.EntityMapper
import com.example.domain.entity.PhotoDomain

class PhotoMapper : EntityMapper<Photo, PhotoDomain> {
    override fun mapFromEntity(entity: Photo): PhotoDomain {
        return PhotoDomain(
            id = entity.id,
            sol = entity.sol,
            camera = CameraMapper().mapFromEntity(entity.camera),
            rover = RoverMapper().mapFromEntity(entity.rover),
            earthDate = entity.earthDate,
            imgSrc = entity.imgSrc


        )
    }

    override fun mapToEntity(domainModel: PhotoDomain): Photo {
        return Photo(
            id = domainModel.id,
            sol = domainModel.sol,
            camera = CameraMapper().mapToEntity(domainModel.camera),
            rover = RoverMapper().mapToEntity(domainModel.rover),
            earthDate = domainModel.earthDate,
            imgSrc = domainModel.imgSrc
        )
    }
    fun fromEntityList(initial: List<Photo>): List<PhotoDomain>{
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<PhotoDomain>): List<Photo>{
        return initial.map { mapToEntity(it) }
    }

}