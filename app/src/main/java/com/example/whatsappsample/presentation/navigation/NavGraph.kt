package com.example.whatsappsample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.whatsappsample.presentation.auth.LoginScreen
import com.example.whatsappsample.presentation.auth.RegisterScreen
import com.example.whatsappsample.presentation.chat.ChatScreen
import com.example.whatsappsample.presentation.chat.ChatListScreen
import com.example.whatsappsample.presentation.profile.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    isAuthenticated: Boolean
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
            // If not authenticated, ensure we go to login
            val currentRoute = navController.currentDestination?.route
            if (currentRoute != Screen.Login.route && currentRoute != Screen.Register.route) {
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
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