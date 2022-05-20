package com.example.marsviewer.presentation

import com.example.data.model.ListOfPhotos
import com.example.domain.entity.ListOfPhotosDomain
import kotlinx.coroutines.flow.StateFlow

data class PhotoListUiState(
    val photosFlow: StateFlow<ListOfPhotosDomain?>,
)
