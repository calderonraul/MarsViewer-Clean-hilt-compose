package com.example.marsviewer.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.PhotoMapper
import com.example.domain.useCase.GetPhotosUseCase
import com.example.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PhotoViewModel (private val getPhotosUseCase: GetPhotosUseCase):ViewModel(){

    private val _state = mutableStateOf(PhotoListState())
    val state:State<PhotoListState> = _state

    init {
        getPhoto()
    }

    private fun getPhoto(){
        getPhotosUseCase().onEach { result->
            when(result){
                is Resource.Success->{
                    _state.value= PhotoListState(photos = PhotoMapper().toEntityList(result.data?: emptyList()))
                }
                is Resource.Error->{
                    _state.value= PhotoListState(error = result.message?:"Error occurred")
                }
                is Resource.Loading->{
                    _state.value=PhotoListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}