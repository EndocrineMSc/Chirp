package com.endocrine.chat.presentation.chat_list_detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChatListDetailViewModel : ViewModel() {

    private val _state = MutableStateFlow(ChatListDetailState())
    val state = _state.asStateFlow()

    fun onAction(action: ChatListDetailAction) {
        when (action) {
            is ChatListDetailAction.OnChatClick -> {
                _state.update {
                    it.copy(
                        selectedChatId = action.chatId
                    )
                }
            }

            ChatListDetailAction.OnCreateChatClick -> {
                updateDialogState(DialogState.CreateChat)
            }

            ChatListDetailAction.OnDismissCurrentDialogClick -> {
                updateDialogState(DialogState.Hidden)
            }

            ChatListDetailAction.OnManageChatClick -> {
                state.value.selectedChatId?.let { id ->
                    updateDialogState(DialogState.ManageChat(id))
                }
            }

            ChatListDetailAction.OnProfileSettingsClick -> {
                updateDialogState(DialogState.Profile)
            }
        }
    }

    private fun updateDialogState(dialogState: DialogState) {
        _state.update {
            it.copy(
                dialogState = dialogState
            )
        }
    }
}

