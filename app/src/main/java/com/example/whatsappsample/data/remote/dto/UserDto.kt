package com.example.whatsappsample.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val profilePictureUrl: String = "",
    val status: String = "",
    val lastSeen: Long = 0
)
