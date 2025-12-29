package com.example.whatsappsample.data.remote.dto

import com.example.whatsappsample.domain.auth.model.User
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
) {
    fun toDomain(): User {
        return User(
            id = id,
            name = name,
            email = email,
            phone = phone,
            profilePictureUrl = profilePictureUrl,
            status = status,
            lastSeen = lastSeen
        )
    }
}
