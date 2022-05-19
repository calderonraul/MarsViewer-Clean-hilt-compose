package com.example.data.model

import com.example.data.mappers.EntityMapper
import com.example.domain.entity.RoverDomain

class RoverMapper:EntityMapper<Rover,RoverDomain> {
    override fun mapFromEntity(entity: Rover): RoverDomain {
        return RoverDomain(
            id=entity.id,
            name = entity.name,
            landingDate = entity.landingDate,
            launchDate = entity.launchDate,
            status = entity.status
        )
    }

    override fun mapToEntity(domainModel: RoverDomain): Rover {
        return Rover(
            id = domainModel.id,
            name = domainModel.name,
            landingDate = domainModel.landingDate,
            launchDate = domainModel.launchDate,
            status = domainModel.status
        )

    }
}