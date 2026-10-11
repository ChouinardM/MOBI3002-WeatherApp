package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// -----------------------------
//  DATA MODEL
// -----------------------------
data class DailyModel(
    val dayName: String,
    val condition: String,
    val highC: Int,
    val lowC: Int,
    val iconRes: Int
)

// -----------------------------
//  ACTIVITY
// -----------------------------
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

// -----------------------------
//  MAIN DAILY SCREEN
// -----------------------------
@Composable
fun DailyScreen(onNowClick: () -> Unit) {

    // Sample 7‑day data
    val items = listOf(
        DailyModel("Mon", "Overcast", 12, 5, R.drawable.ic_cloud),
        DailyModel("Tue", "Light Rain", 10, 6, R.drawable.ic_cloud),
        DailyModel("Wed", "Sunny", 15, 9, R.drawable.ic_cloud),
        DailyModel("Thu", "Cloudy", 8, 2, R.drawable.ic_cloud),
        DailyModel("Fri", "Windy", 11, 4, R.drawable.ic_cloud),
        DailyModel("Sat", "Partly Cloudy", 13, 6, R.drawable.ic_cloud),
        DailyModel("Sun", "Clear", 9, 1, R.drawable.ic_cloud)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Location header
        Text(
            text = "Halifax, Nova Scotia",
            fontSize = 22.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // -----------------------------
        //  DAILY LIST (LazyColumn)
        // -----------------------------
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items) { item ->
                DailyItem(item)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom navigation
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

// -----------------------------
//  DAILY ROW ITEM
// -----------------------------
@Composable
fun DailyItem(item: DailyModel) {

    // Celsius → Fahrenheit conversion
    val highF = (item.highC * 9 / 5) + 32
    val lowF = (item.lowC * 9 / 5) + 32

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = item.iconRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            colorFilter = ColorFilter.tint(
                androidx.compose.ui.graphics.Color(0xFFB0B0B0)
            )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = item.dayName, fontSize = 18.sp)
            Text(text = item.condition, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.End) {
            Text(text = "${item.highC}°C / ${item.lowC}°C", fontSize = 16.sp)
            Text(text = "${highF}°F / ${lowF}°F", fontSize = 14.sp)
        }
    }
}

