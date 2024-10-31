package com.example.chefmate.core.data.api.dto

data class ChatbotAnswer(
    val answerText: String,
    val media: List<ChatbotAttachment>?
)