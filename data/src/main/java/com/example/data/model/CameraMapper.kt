package com.example.data.model

import com.example.data.mappers.EntityMapper
import com.example.domain.entity.CameraDomain

class CameraMapper:EntityMapper<Camera,CameraDomain> {
    override fun mapFromEntity(entity: Camera): CameraDomain {
        return CameraDomain(
            cameraId  = entity.idCamera,
            cameraName = entity.nameCamera,
            roverID = entity.roverID,
            fullName = entity.fullName
        )
    }

    override fun mapToEntity(domainModel: CameraDomain): Camera {
        return Camera(
            idCamera = domainModel.cameraId,
            nameCamera = domainModel.cameraName,
            roverID = domainModel.roverID,
            fullName = domainModel.fullName
        )
    }
}