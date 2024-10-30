package com.example.chefmate.featureChatbot.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.chefmate.featureChatbot.presentation.components.ChatMessageItem

@Composable
fun ChatbotScreen(modifier: Modifier = Modifier, viewModel: ChatbotViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(state.messages) { message ->
                ChatMessageItem(message)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = state.userMessage,
                onValueChange = { viewModel.updateTextInput(it) },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message...") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.sendMessage()
                }
            ) {
                Text("Send")
            }
        }
    }
}
