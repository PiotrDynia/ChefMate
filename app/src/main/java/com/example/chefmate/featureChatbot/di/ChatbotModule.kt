package com.example.chefmate.featureChatbot.di

import com.example.chefmate.core.data.api.APIService
import com.example.chefmate.featureChatbot.data.repository.ChatbotRepositoryImpl
import com.example.chefmate.featureChatbot.domain.repository.ChatbotRepository
import com.example.chefmate.featureChatbot.domain.usecase.ChatbotUseCases
import com.example.chefmate.featureChatbot.domain.usecase.GetChatbotResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ChatbotModule {

    @Provides
    fun provideChatbotRepository(apiService: APIService): ChatbotRepository {
        return ChatbotRepositoryImpl(apiService)
    }

    @Provides
    fun provideChatbotUseCases(repository: ChatbotRepository): ChatbotUseCases {
        return ChatbotUseCases(
            getChatbotResponse = GetChatbotResponse(repository)
        )
    }

}