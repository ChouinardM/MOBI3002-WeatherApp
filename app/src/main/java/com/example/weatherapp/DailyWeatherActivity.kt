package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

// Daily weather values
data class DailyModel(
    val dayName: String,
    val condition: String,
    val highC: Int,
    val lowC: Int,
    val iconRes: Int
)

class DailyWeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DailyScreen(
                onNowClick = {
                    startActivity(Intent(this, NowWeatherActivity::class.java))
                }
            )
        }
    }
}

@Composable
fun DailyScreen(onNowClick: () -> Unit) {

    // Placeholder 7-day forecast
    val items = listOf(
        DailyModel("Mon", "Overcast", 12, 5, R.drawable.ic_cloud),
        DailyModel("Tue", "Light Rain", 10, 6, R.drawable.ic_rain),
        DailyModel("Wed", "Sunny", 15, 9, R.drawable.ic_sun),
        DailyModel("Thu", "Cloudy", 8, 2, R.drawable.ic_cloud),
        DailyModel("Fri", "Windy", 11, 4, R.drawable.ic_cloud),
        DailyModel("Sat", "Partly Cloudy", 13, 6, R.drawable.ic_sun),
        DailyModel("Sun", "Clear", 9, 1, R.drawable.ic_sun)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "7‑Day Forecast",
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items) { item ->
                DailyItem(item)
                Divider(color = Color(0xFFDDDDDD), thickness = 1.dp)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onNowClick) {
                Text("Now")
            }
            Button(onClick = { /* Already on Daily */ }) {
                Text("Daily")
            }
        }
    }
}

@Composable
fun DailyItem(item: DailyModel) {

    // Temperature in Fahrenheit
    val highF = (item.highC * 9 / 5) + 32
    val lowF = (item.lowC * 9 / 5) + 32

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = item.iconRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = item.dayName, fontSize = 18.sp)
            Text(text = item.condition, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Celsius top + Fahrenheit bottom
        Column(horizontalAlignment = Alignment.End) {
            Text(text = "${item.highC}°C / ${item.lowC}°C", fontSize = 16.sp)
            Text(text = "${highF}°F / ${lowF}°F", fontSize = 14.sp)
        }
    }
}


