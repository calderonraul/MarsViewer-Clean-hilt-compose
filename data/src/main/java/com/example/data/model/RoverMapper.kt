package com.example.data.model

import com.example.data.mappers.EntityMapper
import com.example.domain.entity.RoverDomain

class RoverMapper:EntityMapper<Rover,RoverDomain> {
    override fun mapFromEntity(entity: Rover): RoverDomain {
        return RoverDomain(
            roverId=entity.idRover,
            roverName = entity.nameRover,
            landingDate = entity.landingDate,
            launchDate = entity.launchDate,
            status = entity.status
        )
    }

    override fun mapToEntity(domainModel: RoverDomain): Rover {
        return Rover(
            idRover = domainModel.roverId,
            nameRover = domainModel.roverName,
            landingDate = domainModel.landingDate,
            launchDate = domainModel.launchDate,
            status = domainModel.status
        )

    }
}