package com.example.marsviewer.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.PhotoDomain
import com.example.domain.useCase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
) :
    ViewModel() {
    private val wordValueFlow: MutableStateFlow<String> = MutableStateFlow("")
    /*
        private val photoData = getPhotosUseCase.invoke(wordValueFlow.value).stateIn(
            viewModelScope, SharingStarted.Lazily,
            emptyList()
        )
    */
    private val photoData:MutableStateFlow<List<PhotoDomain>> =  MutableStateFlow(emptyList())


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPhotosUseCase.initDB()
            getPhotosUseCase.invoke(wordValueFlow.value).collect{
                photoData.value=it
            }
        }
    }

    fun fetchFilteredList() {
        viewModelScope.launch(Dispatchers.IO) {
            getPhotosUseCase.invoke(wordValueFlow.value).collect{
                photoData.value=it
            }
        }
    }

    fun onWordChanged(value: String) {
        wordValueFlow.value = value
    }

    val registerState = PhotoListUiState(
        photosFlow = photoData,
        wordValue = wordValueFlow,
        onWordValueChanged = this::onWordChanged,
        fetchMoreData = this::fetchFilteredList
    )

}