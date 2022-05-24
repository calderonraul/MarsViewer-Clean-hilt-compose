package com.example.marsviewer.presentation

import com.example.domain.entity.PhotoDomain
import kotlinx.coroutines.flow.StateFlow

data class PhotoListUiState(
    val photosFlow: StateFlow<List<PhotoDomain>>,
    val wordValue: StateFlow<String>,
    val onWordValueChanged: (String) -> Unit,
    val fetchMoreData: () -> Unit

    )
