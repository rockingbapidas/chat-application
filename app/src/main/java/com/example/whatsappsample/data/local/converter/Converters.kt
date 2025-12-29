package com.example.whatsappsample.data.local.converter

import androidx.room.TypeConverter
import com.example.whatsappsample.data.local.entity.OutboxStatus
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromOutboxStatus(value: OutboxStatus?): String? {
        return value?.name
    }

    @TypeConverter
    fun toOutboxStatus(value: String?): OutboxStatus? {
        return value?.let { OutboxStatus.valueOf(it) }
    }
}
