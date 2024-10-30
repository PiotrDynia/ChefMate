package com.example.chefmate.featureChatbot.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefmate.featureChatbot.domain.usecase.ChatbotUseCases
import com.example.chefmate.featureChatbot.domain.util.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
            val chatbotResponse =
                ChatMessage(
                    content = useCases.getChatbotResponse(_state.value.userMessage),
                    isUser = false
                )
            _state.update {
                it.copy(
                    messages = it.messages + chatbotResponse,
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
