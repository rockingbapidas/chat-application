package com.example.whatsappsample.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    object Login : Screen("login")
    object Register : Screen("register")
    object ChatList : Screen("chat_list")
    object Profile : Screen("profile")
    object Chat : Screen(
        route = "chat/{chatId}",
        arguments = listOf(navArgument("chatId") { type = NavType.StringType })
    ) {
        fun createRoute(chatId: String) = "chat/$chatId"
    }
}