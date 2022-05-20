package com.example.marsviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.data.model.Camera
import com.example.marsviewer.ui.theme.MarsViewerTheme
import com.example.data.model.Photo
import com.example.data.model.Rover
import com.example.domain.entity.PhotoDomain
import com.example.marsviewer.presentation.PhotoListUiState
import com.example.marsviewer.presentation.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarsViewerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PhotoListInit()
                }
            }
        }
    }
}

@Composable
fun PhotoListInit(viewModel: PhotoViewModel = hiltViewModel()) {
    PhotoList(state = viewModel.registerState)
}

@Composable
fun PhotoItem(photo: PhotoDomain, fullScreen: MutableState<Boolean>) {

    ViewInFullScreen(fullScreen = fullScreen, photo = photo)
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            //.clickable { onClick(index) }

            .height(150.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Surface() {

            Row(
                Modifier
                    .padding(
                        4.dp
                    )
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = photo.imgSrc,
                    contentDescription = photo.camera.fullName,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)

                ) {
                    Text(text = "SOL: " + photo.sol.toString())
                    Text(
                        text = "Robert name: " + photo.rover.name,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(text = "Camera name: " + photo.camera.fullName)
                    TextButton(onClick = { fullScreen.value = true }) {
                        Text(text = "Show in full screen!")
                    }

                }
            }
        }

    }

}

@Composable
fun ViewInFullScreen(
    fullScreen: MutableState<Boolean>,
    photo: PhotoDomain
) {
    if (fullScreen.value) {
        Box(modifier = Modifier
            .clickable { fullScreen.value = false }
        ) {
            AsyncImage(model = photo.imgSrc, contentDescription = null)
        }
    }
}

@Composable
fun PhotoList(
    state: PhotoListUiState
) {
    val photoList by state.photosFlow.collectAsState()
    val fullScreen: MutableState<Boolean> = remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(-1) }
    var imgVisibility by remember { mutableStateOf(false) }

    if (!imgVisibility) {
        LazyColumn() {
            photoList?.let {
                itemsIndexed(it.list) { index, item ->
                    PhotoItem(photo = item, fullScreen)
                    selectedIndex = index
                }
            }
        }
    } else {
        photoList?.list?.let {
            PhotoItem(
                photo = it[selectedIndex],
                fullScreen
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {


    MarsViewerTheme {
        // PhotoItem(photo)
    }
}