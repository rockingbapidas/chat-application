package com.example.whatsappsample.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsappsample.connection.ConnectionState

@Composable
fun ConnectionStatusStripe(connectionState: ConnectionState) {
    val (text, backgroundColor) = when (connectionState) {
        is ConnectionState.Connected -> return // Don't show anything when connected
        is ConnectionState.Connecting -> "Connecting..." to Color(0xFFFFA500) // Orange
        is ConnectionState.Disconnected -> "Waiting for network..." to Color.Gray
        is ConnectionState.Error -> "Connection error: ${connectionState.message}" to MaterialTheme.colorScheme.error
    }

    AnimatedVisibility(
        visible = true,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
