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
                    startApp()
                }
            }
        }
    }
}

@Composable
fun startApp() {
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
                        text = "Robert name: " + photo.rover.name,
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
    state: PhotoListUiState,
    navController: NavController
) {
    val photoList by state.photosFlow.collectAsState()


    LazyColumn() {
        photoList?.let {
            itemsIndexed(it.list) { index, item ->
                PhotoItem(photo = item, navController)
            }
        }
    }


}

@Composable
fun SecondScreen(url: String? = null) {
    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(model = url, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {


    MarsViewerTheme {
        // PhotoItem(photo)
    }
}