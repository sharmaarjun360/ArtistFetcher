package com.example.artistfetcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artistfetcher.ui.theme.ArtistFetcherTheme

class JetBusinessCard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtistFetcherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(200	,228	,202	)
                ) {
                    BusinessCard(fullName = "Arjun Sharma", title = "Android Developer")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCard(
    modifier: Modifier = Modifier,
    fullName: String = "FirstName",
    title: String = "Title"
): Unit {
    ArtistFetcherTheme {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
            LogoNameAndTitle(fullName = fullName, title = title)
                Spacer(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(.1F))
            Column( Modifier
                .padding(start = 16.dp, end = 16.dp)
            ) {
                ContactInformation(imageVector = Icons.Rounded.Call, "+1-899-899-8991")
                ContactInformation(imageVector = Icons.Rounded.Share, "@AndroidDev")
                ContactInformation(
                    imageVector = Icons.Rounded.Email,
                    "sharma.arjun360360@gmail.com"
                )
            }
        }
    }
}

@Composable
fun ContactInformation(imageVector: ImageVector, detail: String) {

    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector, contentDescription = null,
            modifier = Modifier.size(14.dp),tint = Color(12,	92,	45	)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = detail, fontSize = 14.sp)
    }
}

@Composable
fun LogoNameAndTitle(
    modifier: Modifier = Modifier,
    fullName: String = "FirstName",
    title: String = "Title"
) {
    val logo = painterResource(id = R.drawable.android_logo)
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = logo,
            tint = Color(0xFF3ddc84),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Text(text = fullName, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Text(text = title, fontSize = 14.sp, color = Color(12,	92,	45	))
    }

}