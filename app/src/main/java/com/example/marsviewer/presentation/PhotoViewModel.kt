package com.example.marsviewer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.ListOfPhotosDomain
import com.example.domain.useCase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val getPhotosUseCase: GetPhotosUseCase) :
    ViewModel() {
    private val photoData: Flow<ListOfPhotosDomain?> = getPhotosUseCase.invoke()
    private val initialValue = ListOfPhotosDomain(
        list = emptyList()
    )

    val registerState = PhotoListUiState(
        photosFlow = photoData.stateIn(viewModelScope, SharingStarted.Lazily, initialValue)
    )

}