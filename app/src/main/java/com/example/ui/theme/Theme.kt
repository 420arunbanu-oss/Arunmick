package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF10B981),
    secondary = Color(0xFF3B82F6),
    tertiary = Color(0xFF8B5CF6),
    background = Color(0xFF0F172A),
    surface = Color(0xFF1E293B)
)

@Composable
fun SmartDrivoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
