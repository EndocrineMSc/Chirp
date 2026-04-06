package com.endocrine.chat.presentation.chat_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.endocrine.chat.presentation.model.MessageUi
import com.endocrine.chat.presentation.util.getChatBubbleColorForUser
import com.endocrine.core.designsystem.components.avatar.ChirpAvatarPhoto
import com.endocrine.core.designsystem.components.chat.ChirpChatBubble
import com.endocrine.core.designsystem.components.chat.TrianglePosition

@Composable
internal fun OtherUserMessage(
    message: MessageUi.OtherUserMessage,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ChirpAvatarPhoto(
            displayText = message.sender.initials,
            imageUrl = message.sender.imageUrl
        )
        ChirpChatBubble(
            messageContent = message.content,
            sender = message.sender.userName,
            formattedDateTime = message.formattedSentTime.asString(),
            trianglePosition = TrianglePosition.LEFT,
            color = getChatBubbleColorForUser(message.sender.id)
        )
    }
}