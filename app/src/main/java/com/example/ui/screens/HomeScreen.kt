package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
fun HomeScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToRules: () -> Unit,
    onNavigateToHistory: () -> Unit,
    onNavigateToAreas: () -> Unit,
    onNavigateToMore: () -> Unit
) {
    var isAutoAcceptActive by remember { mutableStateOf(false) }
    var isOverlayActive by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "SmartDrivo Free",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "100% Open Source Driver Edition",
                    color = Color(0xFF10B981),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            IconButton(onClick = onNavigateToMore) {
                Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF064E3B)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF34D399),
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "வாழ்நாள் முழுவதும் இலவசம் (Unlimited Free)",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "அனைத்து சப்ஸ்கிரிப்ஷன்களும் நீக்கப்பட்டு திறந்த மூலமாக இயக்கப்படுகிறது.",
                        color = Color(0xFFA7F3D0),
                        fontSize = 11.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
            shape = RoundedCornerShape(14.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "முதன்மை அமைப்புகள் (Core Controls)",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Auto-Accept (தானியங்கி ஏற்கும் வசதி)", color = Color.White, fontWeight = FontWeight.Medium)
                        Text(
                            if (isAutoAcceptActive) "இயக்கத்தில் உள்ளது (Active)" else "முடக்கப்பட்டுள்ளது (Off)",
                            color = if (isAutoAcceptActive) Color(0xFF10B981) else Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                    Switch(
                        checked = isAutoAcceptActive,
                        onCheckedChange = { isAutoAcceptActive = it }
                    )
                }

                Divider(color = Color(0xFF334155), modifier = Modifier.padding(vertical = 12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Floating Overlay (மிதக்கும் திரை)", color = Color.White, fontWeight = FontWeight.Medium)
                        Text("Rapido, Uber, Ola மீது தகவலை காட்டும்", color = Color.Gray, fontSize = 12.sp)
                    }
                    Switch(
                        checked = isOverlayActive,
                        onCheckedChange = { isOverlayActive = it }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "ரைடு வடிகட்டி கருவிகள் (Filter Tools)",
            color = Color(0xFF94A3B8),
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            NavigationCard(
                title = "கட்டண அமைப்புகள்",
                subtitle = "Min Fare & ₹/Km",
                icon = Icons.Default.Settings,
                color = Color(0xFF3B82F6),
                modifier = Modifier.weight(1f),
                onClick = onNavigateToSettings
            )
            NavigationCard(
                title = "வரம்பற்ற விதிகள்",
                subtitle = "Custom Rules",
                icon = Icons.Default.Check,
                color = Color(0xFF8B5CF6),
                modifier = Modifier.weight(1f),
                onClick = onNavigateToRules
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            NavigationCard(
                title = "பகுதி வடிகட்டி",
                subtitle = "Area Blacklist",
                icon = Icons.Default.Place,
                color = Color(0xFFEF4444),
                modifier = Modifier.weight(1f),
                onClick = onNavigateToAreas
            )
            NavigationCard(
                title = "பயண வரலாறு",
                subtitle = "Ride History",
                icon = Icons.Default.DateRange,
                color = Color(0xFFF59E0B),
                modifier = Modifier.weight(1f),
                onClick = onNavigateToHistory
            )
        }
    }
}

@Composable
fun NavigationCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(subtitle, color = Color(0xFF94A3B8), fontSize = 11.sp)
        }
    }
}
