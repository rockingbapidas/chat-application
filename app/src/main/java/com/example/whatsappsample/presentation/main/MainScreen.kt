package com.example.whatsappsample.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.whatsappsample.presentation.calls.CallListScreen
import com.example.whatsappsample.presentation.chat.ChatListScreen
import com.example.whatsappsample.presentation.components.BottomNavigation
import com.example.whatsappsample.presentation.navigation.Screen
import com.example.whatsappsample.presentation.profile.ProfileScreen

@Composable
fun MainScreen(
    rootNavController: NavController
) {
    val bottomNavController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = bottomNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.ChatList.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.ChatList.route) {
                ChatListScreen(
                    onNavigateToChat = { chatId ->
                        rootNavController.navigate(Screen.Chat.createRoute(chatId))
                    },
                    onNavigateToProfile = {
                        bottomNavController.navigate(Screen.Profile.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(Screen.CallList.route) {
                CallListScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    onNavigateBack = {
                        bottomNavController.popBackStack()
                    }
                )
            }
        }
    }
}
