package com.example.whatsappsample.domain.auth.model

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val profilePictureUrl: String = "",
    val status: String = "",
    val lastSeen: Long = 0
)

