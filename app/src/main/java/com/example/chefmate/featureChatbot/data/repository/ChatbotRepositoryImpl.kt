package com.example.chefmate.featureChatbot.data.repository

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.core.data.api.dto.ChatbotAnswer
import com.example.chefmate.featureChatbot.domain.repository.ChatbotRepository

class ChatbotRepositoryImpl(
    private val apiService: APIService
) : ChatbotRepository  {

    override suspend fun getChatbotResponse(userMessage: String): ChatbotAnswer {
        println("User message: $userMessage")
        return apiService.getChatbotAnswer(input = userMessage)
    }
}