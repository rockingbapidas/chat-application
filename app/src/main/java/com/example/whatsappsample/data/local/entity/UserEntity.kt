package com.example.whatsappsample.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val profilePictureUrl: String,
    val status: String,
    val lastSeen: Long
)

