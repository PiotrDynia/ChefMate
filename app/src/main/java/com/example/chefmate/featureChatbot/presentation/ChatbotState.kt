package com.example.chefmate.featureChatbot.presentation

import com.example.chefmate.core.data.api.dto.ChatbotAttachment
import com.example.chefmate.featureChatbot.domain.util.ChatMessage

data class ChatbotState(
    val messages: List<ChatMessage> = listOf(ChatMessage(content = "Hi! How can I help you?", isUser = false)),
    val userMessage: String = ""
)