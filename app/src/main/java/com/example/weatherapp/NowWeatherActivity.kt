package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    // Temperature in Celsius and Fahrenheit
    val tempC = 6
    val tempF = (tempC * 9 / 5) + 32

    // Feels like in Celsius and Fahrenheit
    val feelsLikeC = 2
    val feelsLikeF = (feelsLikeC * 9 / 5) + 32

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Weather for location
        Text(
            text = "Halifax, Nova Scotia",
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Image(
                    painter = painterResource(id = R.drawable.ic_cloud),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF7A7A7A))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Overcast", fontSize = 20.sp)

                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "$tempC°C / $tempF°F", fontSize = 48.sp)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Feels like $feelsLikeC°C / $feelsLikeF°F\nWind SW 18 kph",
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { /* Already on Now */ }) {
                Text("Now")
            }
            Button(onClick = onDailyClick) {
                Text("Daily")
            }
        }
    }
}


