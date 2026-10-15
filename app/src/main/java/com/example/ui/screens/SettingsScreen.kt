package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    var minFare by remember { mutableStateOf(50f) }
    var minRatePerKm by remember { mutableStateOf(12f) }
    var maxPickupKm by remember { mutableStateOf(3.5f) }

    var isRapidoEnabled by remember { mutableStateOf(true) }
    var isUberEnabled by remember { mutableStateOf(true) }
    var isOlaEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("வடிகட்டி அமைப்புகள் (Filters)", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0F172A))
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Price Controls
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("குறைந்தபட்ச கட்டணம்: ₹${minFare.toInt()}", color = Color.White, fontWeight = FontWeight.Bold)
                    Slider(
                        value = minFare,
                        onValueChange = { minFare = it },
                        valueRange = 20f..300f,
                        steps = 28
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text("கி.மீ-க்கு குறைந்தபட்ச விலை: ₹${minRatePerKm.toInt()}/km", color = Color.White, fontWeight = FontWeight.Bold)
                    Slider(
                        value = minRatePerKm,
                        onValueChange = { minRatePerKm = it },
                        valueRange = 8f..40f,
                        steps = 32
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text("அதிகபட்ச பிக்கப் தொலைவு: ${String.format("%.1f", maxPickupKm)} km", color = Color.White, fontWeight = FontWeight.Bold)
                    Slider(
                        value = maxPickupKm,
                        onValueChange = { maxPickupKm = it },
                        valueRange = 0.5f..10f,
                        steps = 19
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Platform Toggles
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("செயலில் உள்ள ஆப்கள் (Platforms)", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Rapido", color = Color.White)
                        Switch(checked = isRapidoEnabled, onCheckedChange = { isRapidoEnabled = it })
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Uber", color = Color.White)
                        Switch(checked = isUberEnabled, onCheckedChange = { isUberEnabled = it })
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Ola", color = Color.White)
                        Switch(checked = isOlaEnabled, onCheckedChange = { isOlaEnabled = it })
                    }
                }
            }
        }
    }
}
