package com.example.whatsappsample.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.whatsappsample.connection.ConnectionState
import com.example.whatsappsample.presentation.auth.LoginScreen
import com.example.whatsappsample.presentation.auth.RegisterScreen
import com.example.whatsappsample.presentation.calls.CallListScreen
import com.example.whatsappsample.presentation.chat.ChatScreen
import com.example.whatsappsample.presentation.chat.ChatListScreen
import com.example.whatsappsample.presentation.components.BottomNavigation
import com.example.whatsappsample.presentation.components.ConnectionStatusStripe
import com.example.whatsappsample.presentation.profile.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    isAuthenticated: Boolean,
    connectionState: ConnectionState
) {
    val startDestination = if (isAuthenticated) Screen.ChatList.route else Screen.Login.route

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            // If authenticated, ensure we're not on auth screens
            val currentRoute = navController.currentDestination?.route
            if (currentRoute == Screen.Login.route || currentRoute == Screen.Register.route) {
                navController.navigate(Screen.ChatList.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
        } else {
            // If not authenticated, ensure we go to log in
            val currentRoute = navController.currentDestination?.route
            if (currentRoute != Screen.Login.route && currentRoute != Screen.Register.route) {
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        Screen.ChatList.route,
        Screen.CallList.route,
        Screen.Profile.route
    )

    Scaffold(
        topBar = {
            if (isAuthenticated) {
                ConnectionStatusStripe(connectionState = connectionState)
            }
        },
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onNavigateToRegister = {
                        navController.navigate(Screen.Register.route)
                    },
                    onLoginSuccess = {
                        // Handled by LaunchedEffect(isAuthenticated)
                    }
                )
            }

            composable(Screen.Register.route) {
                RegisterScreen(
                    onNavigateToLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    },
                    onRegisterSuccess = {
                        // Handled by LaunchedEffect(isAuthenticated)
                    }
                )
            }

            composable(Screen.ChatList.route) {
                ChatListScreen(
                    onNavigateToChat = { chatId ->
                        navController.navigate(Screen.Chat.createRoute(chatId))
                    },
                    onNavigateToProfile = {
                        navController.navigate(Screen.Profile.route)
                    }
                )
            }

            composable(Screen.CallList.route) {
                CallListScreen()
            }

            composable(
                route = Screen.Chat.route,
                arguments = Screen.Chat.arguments
            ) {
                ChatScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}