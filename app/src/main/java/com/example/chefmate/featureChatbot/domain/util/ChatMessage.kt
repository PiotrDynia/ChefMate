package com.example.chefmate.featureChatbot.domain.util

import com.example.chefmate.core.data.api.dto.ChatbotAttachment

data class ChatMessage(
    val content: String,
    val attachments: List<ChatbotAttachment>? = null,
    val isUser: Boolean
)