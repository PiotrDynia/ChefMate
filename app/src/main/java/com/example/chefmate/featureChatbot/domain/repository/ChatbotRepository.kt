package com.example.chefmate.featureChatbot.domain.repository

import com.example.chefmate.core.data.api.dto.ChatbotAnswer

interface ChatbotRepository {
    suspend fun getChatbotResponse(userMessage: String): ChatbotAnswer
}