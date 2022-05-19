package com.example.data.model

import com.example.data.mappers.EntityMapper
import com.example.domain.entity.CameraDomain

class CameraMapper:EntityMapper<Camera,CameraDomain> {
    override fun mapFromEntity(entity: Camera): CameraDomain {
        return CameraDomain(
            id = entity.id,
            name = entity.name,
            roverID = entity.roverID,
            fullName = entity.fullName
        )
    }

    override fun mapToEntity(domainModel: CameraDomain): Camera {
        return Camera(
            id = domainModel.id,
            name = domainModel.name,
            roverID = domainModel.roverID,
            fullName = domainModel.fullName
        )
    }
}