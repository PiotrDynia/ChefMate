package com.example.chefmate.featureChatbot.domain.usecase

import com.example.chefmate.core.data.api.dto.ChatbotAnswer
import com.example.chefmate.core.domain.util.methodResult.Result
import com.example.chefmate.core.domain.util.methodResult.DataError
import com.example.chefmate.featureChatbot.domain.repository.ChatbotRepository
import retrofit2.HttpException
import java.io.IOException

class GetChatbotResponse(private val repository: ChatbotRepository) {

    suspend operator fun invoke(input: String): Result<ChatbotAnswer, DataError.Network> {
        return try {
            val answer = repository.getChatbotResponse(input)
            Result.Success(answer)
        } catch (e: HttpException) {
            val error = when (e.code()) {
                408 -> DataError.Network.REQUEST_TIMEOUT
                429 -> DataError.Network.TOO_MANY_REQUESTS
                413 -> DataError.Network.PAYLOAD_TOO_LARGE
                in 500..599 -> DataError.Network.SERVER_ERROR
                else -> DataError.Network.UNKNOWN
            }
            Result.Error(error)
        } catch (e: IOException) {
            Result.Error(DataError.Network.NO_INTERNET)
        }
    }
}
