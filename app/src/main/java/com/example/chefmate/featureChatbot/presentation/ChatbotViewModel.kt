package com.example.chefmate.featureChatbot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.core.data.api.dto.ChatbotAnswer
import com.example.chefmate.core.domain.util.methodResult.Result
import com.example.chefmate.featureChatbot.domain.usecase.ChatbotUseCases
import com.example.chefmate.featureChatbot.domain.util.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatbotViewModel @Inject constructor(
    private val useCases: ChatbotUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ChatbotState())
    val state = _state.asStateFlow()

    fun sendMessage() {
        if (_state.value.userMessage.isNotBlank()) {
            sendUserMessage()
            getChatbotResponse()
        }
    }

    private fun sendUserMessage() {
        val userMessage = ChatMessage(content = _state.value.userMessage, isUser = true)
        _state.update {
            it.copy(
                messages = it.messages + userMessage
            )
        }
    }

    private fun getChatbotResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = useCases.getChatbotResponse(_state.value.userMessage)) {
                is Result.Success -> loadChatbotAnswer(result.data)
                is Result.Error -> loadErrorMessage()
            }
        }
    }

    private suspend fun loadChatbotAnswer(answer: ChatbotAnswer) {
        val content = buildString {
            append(answer.answerText)

            if (!answer.media.isNullOrEmpty()) {
                append(" Search for them in our search section.")
            }
        }

        val chatbotResponse = ChatMessage(
            content = content,
            attachments = answer.media.takeIf { it != null },
            isUser = false
        )

        withContext(Dispatchers.Main) {
            _state.update {
                it.copy(
                    messages = it.messages + chatbotResponse,
                    userMessage = ""
                )
            }
        }
    }

    private suspend fun loadErrorMessage() {
        val errorMessage = ChatMessage(
            content = "An error occurred, chatbot response can't be loaded, please try again",
            isUser = false
        )
        withContext(Dispatchers.Main) {
            _state.update {
                it.copy(
                    messages = it.messages + errorMessage,
                    userMessage = ""
                )
            }
        }
    }

    fun updateTextInput(input: String) {
        _state.update {
            it.copy(
                userMessage = input
            )
        }
    }
}
