package com.example.marsviewer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PhotoDomain
import com.example.domain.useCase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
) :
    ViewModel() {
    private val photoData = getPhotosUseCase.invoke().stateIn(
        viewModelScope, SharingStarted.Lazily,
        emptyList()
    )
    private val wordValueFlow: MutableStateFlow<String> = MutableStateFlow("")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPhotosUseCase.initDB()
        }
    }

    fun onWordChanged(value: String) {
        wordValueFlow.value = value
    }

    val registerState = PhotoListUiState(
        photosFlow = photoData,
        wordValue = wordValueFlow,
        onWordValueChanged = this::onWordChanged
    )

}