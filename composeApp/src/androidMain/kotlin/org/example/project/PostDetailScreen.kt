package org.example.project

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import org.example.project.component.BaseScreen
import org.example.project.effect.PhotoEffects
import org.example.project.intent.PhotoIntent
import org.example.project.viewmodel.PhotoViewModel
import org.koin.androidx.compose.koinViewModel

// Created by Zain Shakoor
// on 7/24/2025


@Composable
fun PostDetailScreen(viewModel: PhotoViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current // ✅ moved here, inside Composable scope


    LaunchedEffect(Unit) {
        viewModel.onIntent(PhotoIntent.loadPhotos)
    }
    LaunchedEffect(Unit) {

        viewModel.effect.collect { effects ->
            when (effects) {
                is PhotoEffects.ShowError -> {
                    Toast.makeText(context, effects.mesg, Toast.LENGTH_LONG).show()

                }

                is PhotoEffects.ShowToast -> {
                    Toast.makeText(context, effects.mesg, Toast.LENGTH_LONG).show()


                }
            }


        }

    }

//    val list = listOf(
//        PostModel(
//            id = "1",
//            title = "Flutter",
//            albumId = "21",
//            resource = Res.drawable.item1,
//        ),
//        PostModel(
//            id = "2",
//            title = "KMP",
//            albumId = "22",
//            resource = Res.drawable.item2,
//        ),
//        PostModel(
//            id = "3",
//            title = "CMP",
//            albumId = "23",
//            resource = Res.drawable.item3,
//        ),
//        PostModel(
//            id = "4",
//            title = "React Native",
//            albumId = "24",
//            resource = Res.drawable.item4,
//        ),
//    )

    BaseScreen() {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            TopBarComponent("Api Data")
            Spacer(Modifier.height(20.dp))

            when {

                state.isLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> {
                    Text("Error: ${state.error}")
                }

                else -> {
                    LazyColumn {
                        items(state.photos) {
                            DetailCard(
                                it.title,
                                it.id.toString(),
                                it.albumId.toString(),
                                it.thumbnailUrl
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopBarComponent(title: String) {
    Box(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
        )
        Text(
            text = title,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview
@Composable
fun previewTopBar() {
    TopBarComponent("Api Data")
}

//@Preview
//@Composable
//fun previewPostScreen() {
//    PostDetailScreen()
//}

@Composable
fun DetailCard(
    title: String, id: String, albumId: String, imageRes: String
) { Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(70.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp))
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageRes),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = id,
                style = TextStyle(fontSize = 16.sp)
            )
            Text(
                text = albumId,
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}


//@Preview
//@Composable
//fun PreviewDetailCard(modifier: Modifier = Modifier) {
//    DetailCard("1,")
//
//}
