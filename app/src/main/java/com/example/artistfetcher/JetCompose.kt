package com.example.artistfetcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artistfetcher.ui.theme.ArtistFetcherTheme

class JetCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtistFetcherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(message = stringResource(R.string.happy_birthday_text), from = stringResource(
                        R.string.signature_text
                    ))
                }
            }
        }
    }
}

@Composable
fun GreetingImage(modifier: Modifier = Modifier, message: String, from: String) {
    val image = painterResource(id = R.drawable.androidparty2)
    Box(modifier = modifier.padding(8.dp)) {
        Image(painter = image, contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            from = "Arjun", modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Composable
fun GreetingText(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.happy_birthday_text),
    from: String = stringResource(id = R.string.signature_text)
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontFamily = FontFamily.Cursive,
            fontSize = 80.sp,
            lineHeight = 100.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 36.sp,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreviewer() {
    ArtistFetcherTheme {
//        GreetingText(message = "जन्मदिवस की हार्दिक शुभकामनाएँ कृति ")
        GreetingImage(message = stringResource(id = R.string.happy_birthday_text), from = stringResource(
            id = R.string.signature_text))
    }
}