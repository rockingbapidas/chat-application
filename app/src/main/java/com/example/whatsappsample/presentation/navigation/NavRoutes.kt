package com.example.whatsappsample.presentation.navigation

sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object Register : NavRoutes("register")
    object ChatList : NavRoutes("chat_list")
    object Chat : NavRoutes("chat/{chatId}") {
        fun createRoute(chatId: String) = "chat/$chatId"
    }
    object Profile : NavRoutes("profile")
    object Status : NavRoutes("status")
    object Calls : NavRoutes("calls")
} 