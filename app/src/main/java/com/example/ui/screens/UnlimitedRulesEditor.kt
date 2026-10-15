package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DriverRule(
    val id: String,
    val name: String,
    val condition: String,
    val action: String,
    val isEnabled: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnlimitedRulesEditor(onBack: () -> Unit) {
    val rules = remember {
        mutableStateListOf(
            DriverRule("1", "Peak Hour High Fare Rule", "₹/Km > 18 AND Pickup < 2.5km", "Auto Accept Instantly"),
            DriverRule("2", "Long Trip High Earnings", "Trip Distance > 25km AND Fare > ₹350", "Auto Accept Instantly"),
            DriverRule("3", "Airport Zone Priority", "Destination contains 'Airport' OR 'Aerodrome'", "High Priority Sound Alert"),
            DriverRule("4", "Avoid Short Non-Profitable Trips", "Fare < ₹40 AND Distance > 4km", "Reject Automatically")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("வரம்பற்ற விதிகள் (Unlimited Rules)", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0F172A))
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    rules.add(
                        DriverRule(
                            System.currentTimeMillis().toString(),
                            "புதிய விதி #${rules.size + 1}",
                            "Fare >= ₹100 AND ₹/km >= 15",
                            "Auto Accept"
                        )
                    )
                },
                containerColor = Color(0xFF10B981),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Rule")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0F172A))
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "முன்பு கட்டணத் திட்டத்தில் முடக்கப்பட்டிருந்த அனைத்து விதிகளும் இப்போது இலவசமாக வரம்பின்றி கிடைக்கின்றன.",
                        color = Color(0xFF34D399),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            items(rules, key = { it.id }) { rule ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(rule.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("நிபந்தனை: ${rule.condition}", color = Color(0xFF93C5FD), fontSize = 12.sp)
                            Text("செயல்: ${rule.action}", color = Color(0xFF34D399), fontSize = 12.sp)
                        }
                        IconButton(onClick = { rules.remove(rule) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color(0xFFEF4444))
                        }
                    }
                }
            }
        }
    }
}
