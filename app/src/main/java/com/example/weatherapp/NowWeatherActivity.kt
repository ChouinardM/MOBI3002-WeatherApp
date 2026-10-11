package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class NowWeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NowScreen(
                onDailyClick = {
                    startActivity(Intent(this, DailyWeatherActivity::class.java))
                }
            )
        }
    }
}

@Composable
fun NowScreen(onDailyClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Halifax, Nova Scotia",
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_cloud),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            colorFilter = ColorFilter.tint(androidx.compose.ui.graphics.Color(0xFFB0B0B0))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Overcast",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "6°C",
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Feels like 2°C\nWind SW 18 kph",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { /* already on Now */ }) {
                Text("Now")
            }
            Button(onClick = onDailyClick) {
                Text("Daily")
            }
        }
    }
}
