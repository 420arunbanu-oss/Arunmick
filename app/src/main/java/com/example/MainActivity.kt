package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.ui.screens.*
import com.example.ui.theme.SmartDrivoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartDrivoTheme {
                val navController = rememberNavController()
                
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeScreen(
                                onNavigateToSettings = { navController.navigate("settings") },
                                onNavigateToRules = { navController.navigate("rules") },
                                onNavigateToHistory = { navController.navigate("history") },
                                onNavigateToAreas = { navController.navigate("areas") },
                                onNavigateToMore = { navController.navigate("more") }
                            )
                        }
                        composable("settings") {
                            SettingsScreen(onBack = { navController.popBackStack() })
                        }
                        composable("rules") {
                            UnlimitedRulesEditor(onBack = { navController.popBackStack() })
                        }
                        composable("history") {
                            OrderHistoryScreen(onBack = { navController.popBackStack() })
                        }
                        composable("areas") {
                            AreaManagerScreen(onBack = { navController.popBackStack() })
                        }
                        composable("more") {
                            MoreScreen(onBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
