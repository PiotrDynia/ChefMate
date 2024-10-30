package com.example.chefmate.featureChatbot.domain.usecase

import com.example.chefmate.featureChatbot.domain.repository.ChatbotRepository

class GetChatbotResponse(private val repository: ChatbotRepository) {

    suspend operator fun invoke(input: String): String {
        return repository.getChatbotResponse(input).answerText
    }
}
