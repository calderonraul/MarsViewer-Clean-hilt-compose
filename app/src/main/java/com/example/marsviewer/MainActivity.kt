package com.example.marsviewer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.marsviewer.ui.theme.MarsViewerTheme
import com.example.domain.entity.PhotoDomain
import com.example.marsviewer.presentation.PhotoListUiState
import com.example.marsviewer.presentation.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


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
                    StartApp()
                }
            }
        }
    }
}

@Composable
fun StartApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "PhotoListInit") {
        composable("PhotoListInit") { PhotoListInit(navController = navController) }
        composable("SecondScreen/{encodedUrl}", listOf(navArgument("encodedUrl") {
            type = NavType.StringType
        })) {
            val item = it.arguments?.getString("encodedUrl")
            SecondScreen(url = item)
        }

    }
}


@Composable
fun PhotoListInit(viewModel: PhotoViewModel = hiltViewModel(), navController: NavController) {
    PhotoList(state = viewModel.registerState, navController)
}


@Composable
fun PhotoItem(photo: PhotoDomain, navController: NavController) {

    var encodedUrl: String

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
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
                        .clickable { }
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
                        text = "Robert name: " + photo.rover.roverName,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(text = "Camera name: " + photo.camera.fullName)
                    encodedUrl = URLEncoder.encode(photo.imgSrc, StandardCharsets.UTF_8.toString())
                    TextButton(onClick = { navController.navigate("SecondScreen/$encodedUrl") }) {
                        Text(text = "Show in full screen!")
                    }

                }
            }
        }

    }

}


@Composable
fun PhotoList(
    state: PhotoListUiState,
    navController: NavController
) {

    val photoList by state.photosFlow.collectAsState()
    val word by state.wordValue.collectAsState()

    val textChipRememberOneState = remember {
        mutableStateOf(false)
    }
    val textChipRememberOneState2 = remember {
        mutableStateOf(false)
    }
    val textChipRememberOneState3 = remember {
        mutableStateOf(false)
    }
    val textChipRememberOneState4 = remember {
        mutableStateOf(false)
    }
    val textChipRememberOneState5 = remember {
        mutableStateOf(false)
    }

    val listState = remember {
        mutableStateOf(true)
    }

    Column() {
        Row(modifier = Modifier.weight(0.1f)) {
            Column() {
                Text(
                    text = "Select which camera photos you want to see",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    TextChipWithIconVisibility(
                        iconId = com.example.data.R.drawable.ic_done,
                        isSelected = textChipRememberOneState.value,
                        text = "FHAZ",
                        onChecked = { textChipRememberOneState.value = it },
                        state = state,
                        onFiltered = { listState.value = it }
                    )
                    TextChipWithIconVisibility(
                        iconId = com.example.data.R.drawable.ic_done,
                        isSelected = textChipRememberOneState2.value,
                        text = "RHAZ",
                        onChecked = { textChipRememberOneState2.value = it },
                        state = state,
                        onFiltered = { listState.value = it }

                    )
                    TextChipWithIconVisibility(
                        iconId = com.example.data.R.drawable.ic_done,
                        isSelected = textChipRememberOneState3.value,
                        text = "MAST",
                        onChecked = { textChipRememberOneState3.value = it },
                        state = state,
                        onFiltered = { listState.value = it }
                    )
                    TextChipWithIconVisibility(
                        iconId = com.example.data.R.drawable.ic_done,
                        isSelected = textChipRememberOneState4.value,
                        text = "CHEMCAM",
                        onChecked = { textChipRememberOneState4.value = it },
                        state = state,
                        onFiltered = { listState.value = it }
                    )
                    TextChipWithIconVisibility(
                        iconId = com.example.data.R.drawable.ic_done,
                        isSelected = textChipRememberOneState5.value,
                        text = "MAHLI",
                        onChecked = { textChipRememberOneState5.value = it },
                        state = state,
                        onFiltered = { listState.value = it }
                    )
                }
            }
        }
        Row(modifier = Modifier.weight(0.9f)) {

            if (listState.value) {
                LazyColumn(modifier = Modifier) {
                    itemsIndexed(photoList) { _, item ->
                        PhotoItem(photo = item, navController)
                    }
                }
            } else {

            }


        }
    }
}


@Composable
fun SecondScreen(url: String? = null) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = url, contentDescription = null,
            contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun TextChipWithIconVisibility(
    @DrawableRes iconId: Int,
    isSelected: Boolean,
    text: String,
    onChecked: (Boolean) -> Unit,
    state: PhotoListUiState,
    onFiltered: (Boolean) -> Unit
) {
    val shape = RoundedCornerShape(8.dp)
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                vertical = 2.dp,
                horizontal = 4.dp
            )
            .border(
                width = 1.dp,
                color = LightGray,
                shape = shape
            )
            .background(
                color = LightGray,
                shape = shape
            )
            .clip(shape = shape)
            .clickable {
                onChecked(!isSelected)
                onFiltered(isSelected)
                state.onWordValueChanged(text)

            }
            .padding(4.dp)
    ) {
        if (isSelected) {
            Icon(
                painter = painterResource(id = iconId),
                tint = DarkGray,
                contentDescription = null
            )
        }
        Text(
            text = text,
            color = Unspecified
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarsViewerTheme {
        // PhotoItem(photo)
    }
}