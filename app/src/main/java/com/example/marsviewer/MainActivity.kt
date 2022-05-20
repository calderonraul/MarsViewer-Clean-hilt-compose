package com.example.marsviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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

        val cameraTest = Camera(
            1,
            "Lorem Ipsum",
            24,
            "Mast Camera"
        )
        val roverTest = Rover(
            1,
            "Curiosity",
            "01-01-2000",
            "01-01-1999",
            "active"
        )
        val photo = Photo(
            1,
            1000,
            cameraTest,
            "http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631250503685E01_DXXX.jpg",
            "2015-05-30",
            roverTest

        )


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
fun PhotoItem(photo: PhotoDomain) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(110.dp),
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

                }
            }
        }

    }

}


@Composable
fun PhotoList(
    state: PhotoListUiState
) {


    val photoList by state.photosFlow.collectAsState()
    var selectedIndex by remember { mutableStateOf(-1) }


    LazyColumn() {
        photoList?.let {

            itemsIndexed(it.list) { index, item ->
                PhotoItem(photo = item)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val cameraTest = Camera(
        1,
        "Lorem Ipsum",
        24,
        "Mast Camera"
    )
    val roverTest = Rover(
        1,
        "Curiosity",
        "01-01-2000",
        "01-01-1999",
        "active"
    )
    val photo = Photo(
        1,
        1000,
        cameraTest,
        "http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631250503685E01_DXXX.jpg",
        "2015-05-30",
        roverTest

    )


    MarsViewerTheme {
        // PhotoItem(photo)
    }
}