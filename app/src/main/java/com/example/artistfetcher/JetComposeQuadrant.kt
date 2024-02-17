package com.example.artistfetcher

import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artistfetcher.ui.theme.ArtistFetcherTheme

class JetComposeQuadrant : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtistFetcherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeQuadrant()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeQuadrant(modifier: Modifier = Modifier) {
    ArtistFetcherTheme {
        Column(Modifier.fillMaxSize()) {
            HalfData(
                modifier = Modifier.weight(50F),
                color = Color(0xFFEADDFF),
                color2 = Color(0xFFD0BCFF),
                title1 = "Text composable",
                message1 = "Displays text and follows the recommended Material Design guidelines.",
                title2 = "Image composable",
                message2 = "Creates a composable that lays out and draws a given Painter class object."
            )
            HalfData(
                modifier = Modifier.weight(50F),
                color = Color(0xFFB69DF8),
                color2 = Color(0xFFF6EDFF),
                title1 = "Row composable",
                message1 = "A layout composable that places its children in a horizontal sequence.",
                title2 = "Column composable",
                message2 = "A layout composable that places its children in a vertical sequence."
            )
        }
    }
}

@Composable
fun HalfData(
    modifier: Modifier, color: Color, color2: Color,
    title1: String,
    message1: String,
    title2: String,
    message2: String
) {
    Row(modifier.fillMaxSize()) {
        oneQuadData(
            modifier = modifier
                .weight(50F)
                .background(color = color), title1, message1
        )
        oneQuadData(
            modifier = modifier
                .weight(50F)
                .background(color = color2), title2, message2
        )
    }
}

@Composable
fun oneQuadData(modifier: Modifier, title: String, message: String) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, Modifier.padding(bottom = 16.dp))
        Text(text = message)
    }
}