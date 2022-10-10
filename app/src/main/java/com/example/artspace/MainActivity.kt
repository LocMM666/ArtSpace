package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {

    ArtSpaceButton(modifier = Modifier)

}

@Composable
fun ArtSpaceWithImage(
    image: Int,
    modifier: Modifier
) {

    Box(
        modifier = modifier
            .wrapContentSize()
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .border(2.dp, Color.Gray)
                .padding(32.dp),
            contentScale = ContentScale.Fit
        )

    }
}

@Composable
fun ImageInformation(
    name: Int,
    artist: Int,
    year: Int,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .wrapContentSize(),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(id = name),
                fontSize = 24.sp,
            )
            Row() {
                Text(text = stringResource(id = artist))
                Spacer(modifier = Modifier.width(4.dp))
                "(" + Text(text = stringResource(id = year)) + ")"
            }
        }
    }

}
@Composable
fun ArtSpaceButton(
    modifier: Modifier
) {
    var result by remember { mutableStateOf(1) }
    val imageResource = when(result) {
        1 -> R.drawable.flower_1
        2 -> R.drawable.flower_2
        3 -> R.drawable.flower_3
        4 -> R.drawable.flower_4
        5 -> R.drawable.flower_5
        else -> R.drawable.flower_6
    }

    val imageName = when(result) {
        1 -> R.string.flower_1_name
        2 -> R.string.flower_2_name
        3 -> R.string.flower_3_name
        4 -> R.string.flower_4_name
        5 -> R.string.flower_5_name
        else -> R.string.flower_6_name
    }

    val imageArtist = when(result) {
        1 -> R.string.flower_1_artist
        2 -> R.string.flower_2_artist
        3 -> R.string.flower_3_artist
        4 -> R.string.flower_4_artist
        5 -> R.string.flower_5_artist
        else -> R.string.flower_6_artist
    }

    val imageYear = when(result) {
        1 -> R.string.flower_1_year
        2 -> R.string.flower_2_year
        3 -> R.string.flower_3_year
        4 -> R.string.flower_4_year
        5 -> R.string.flower_5_year
        else -> R.string.flower_6_year
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 36.dp, end = 24.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtSpaceWithImage(
            image = imageResource,
            modifier = Modifier.weight(8f)
        )
        Spacer(modifier = Modifier.height(36.dp))
        ImageInformation(
            name = imageName,
            artist = imageArtist,
            year = imageYear,
            modifier = Modifier.weight(2f)
        )
        Spacer(modifier = Modifier.height(36.dp))
        Row(
            modifier = modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.BottomCenter)
                .weight(1f)
        ) {
            Button(
                onClick = {
                    if(result in (2..6)) {
                        result--
                    } else {
                        result = 6
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Preview")
            }
            Button(
                onClick = {
                    if(result in (1..5)) {
                        result++
                    } else {
                        result = 1
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}