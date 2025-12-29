package com.example.whatsappsample.data.mapper

import com.example.whatsappsample.data.local.entity.MessageEntity
import com.example.whatsappsample.data.local.entity.OutboxMessageEntity
import com.example.whatsappsample.data.local.entity.OutboxStatus
import com.example.whatsappsample.data.remote.dto.MessageDto
import com.example.whatsappsample.domain.chat.model.Message
import com.example.whatsappsample.domain.chat.model.MessageStatus
import com.example.whatsappsample.domain.chat.model.MessageType

fun MessageEntity.toDomain(): Message {
    return Message(
        id = id,
        chatId = chatId,
        senderId = senderId,
        receiverId = receiverId,
        content = content,
        type = MessageType.valueOf(type),
        timestamp = timestamp,
        status = MessageStatus.valueOf(status)
    )
}

fun Message.toEntity(): MessageEntity {
    return MessageEntity(
        id = id,
        chatId = chatId,
        senderId = senderId,
        receiverId = receiverId,
        content = content,
        type = type.name,
        timestamp = timestamp,
        status = status.name
    )
}

fun MessageDto.toEntity(): MessageEntity {
    return MessageEntity(
        id = id,
        chatId = chatId,
        senderId = senderId,
        receiverId = receiverId,
        content = content,
        type = type,
        timestamp = timestamp,
        status = status
    )
}

fun OutboxMessageEntity.toDomain(): Message {
    return Message(
        id = id,
        chatId = chatId,
        senderId = senderId,
        receiverId = receiverId,
        content = content,
        type = MessageType.valueOf(type),
        timestamp = timestamp,
        status = when (status) {
            OutboxStatus.PENDING, OutboxStatus.SENDING -> MessageStatus.SENT
            OutboxStatus.SENT -> MessageStatus.SENT
            OutboxStatus.FAILED -> MessageStatus.SENT
        }
    )
}

fun Message.toOutboxEntity(status: OutboxStatus = OutboxStatus.PENDING): OutboxMessageEntity {
    return OutboxMessageEntity(
        id = id,
        chatId = chatId,
        senderId = senderId,
        receiverId = receiverId,
        content = content,
        type = type.name,
        timestamp = timestamp,
        status = status
    )
}
