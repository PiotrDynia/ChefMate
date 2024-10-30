package com.example.chefmate.featureChatbot.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chefmate.featureChatbot.domain.util.ChatMessage

@Composable
fun ChatMessageItem(message: ChatMessage) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Text(
            text = message.content,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier
                .background(
                    color = if (message.isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
    }
}
