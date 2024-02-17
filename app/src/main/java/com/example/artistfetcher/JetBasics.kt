package com.example.artistfetcher

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artistfetcher.ui.theme.ArtistFetcherTheme

class JetBasics : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtistFetcherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowData()
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowData(modifier: Modifier = Modifier) {
    val headerImage = painterResource(id = R.drawable.bg_compose_background)
    Column(modifier = Modifier.padding(bottom = 26.dp).background(color= Color.White).fillMaxSize()) {
        Image(
            painter = headerImage, contentDescription = null,
            contentScale = ContentScale.Fit, modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.jetpack_compose_tutorial),
            color = Color.Black,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.para_1),
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(16.dp, 0.dp, 16.dp, 0.dp)
        )
        Text(
            text = stringResource(R.string.para_2),
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 16.dp)
        )
    }

}