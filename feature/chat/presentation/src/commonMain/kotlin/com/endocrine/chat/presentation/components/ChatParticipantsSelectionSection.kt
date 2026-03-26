package com.endocrine.chat.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.endocrine.core.designsystem.components.avatar.ChatParticipantUi
import com.endocrine.core.designsystem.components.avatar.ChirpAvatarPhoto
import com.endocrine.core.designsystem.theme.extended
import com.endocrine.core.designsystem.theme.titleXSmall
import com.endocrine.core.presentation.util.DeviceConfiguration.DESKTOP
import com.endocrine.core.presentation.util.DeviceConfiguration.TABLET_LANDSCAPE
import com.endocrine.core.presentation.util.DeviceConfiguration.TABLET_PORTRAIT
import com.endocrine.core.presentation.util.currentDeviceConfiguration

@Composable
fun ColumnScope.ChatParticipantsSelectionSection(
    selectedParticipants: List<ChatParticipantUi>,
    modifier: Modifier = Modifier,
    searchResult: ChatParticipantUi? = null,
) {
    val deviceConfiguration = currentDeviceConfiguration()
    val rootHeightModifier = when (deviceConfiguration) {
        TABLET_PORTRAIT, TABLET_LANDSCAPE, DESKTOP -> Modifier
            .animateContentSize()
            .heightIn(min = 200.dp, max = 300.dp)

        else -> Modifier.weight(1f)
    }

    Box(
        modifier = rootHeightModifier.then(modifier)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            searchResult?.let {
                item {
                    ChatParticipantListItem(
                        participantUi = searchResult,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
                if(selectedParticipants.isNotEmpty() && searchResult ==  null) {
                    items(
                        items = selectedParticipants,
                        key = { it.id }
                    ) { participant ->
                        ChatParticipantListItem(
                            participantUi = participant,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
        }
    }
}

@Composable
private fun ChatParticipantListItem(
    participantUi: ChatParticipantUi, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ChirpAvatarPhoto(
            displayText = participantUi.initials,
            imageUrl = participantUi.imageUrl
        )
        Text(
            text = participantUi.userName,
            style = MaterialTheme.typography.titleXSmall,
            color = MaterialTheme.colorScheme.extended.textPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}