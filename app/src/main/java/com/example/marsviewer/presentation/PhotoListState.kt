package com.example.marsviewer.presentation

import com.example.data.model.Photo

data class PhotoListState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = emptyList(),
    val error: String =""
)
