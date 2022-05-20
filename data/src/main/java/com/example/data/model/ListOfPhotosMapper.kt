package com.example.data.model

import com.example.data.mappers.EntityMapper
import com.example.domain.entity.ListOfPhotosDomain

class ListOfPhotosMapper:EntityMapper<ListOfPhotos,ListOfPhotosDomain> {
    override fun mapFromEntity(entity: ListOfPhotos): ListOfPhotosDomain {
        return ListOfPhotosDomain(
            list = PhotoMapper().fromEntityList(entity.photos)
        )

    }

    override fun mapToEntity(domainModel: ListOfPhotosDomain): ListOfPhotos {
        return ListOfPhotos(
            photos = PhotoMapper().toEntityList(domainModel.list)
        )
    }
}