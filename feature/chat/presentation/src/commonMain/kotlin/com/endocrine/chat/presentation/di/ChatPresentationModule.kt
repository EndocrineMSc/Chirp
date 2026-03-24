package com.endocrine.chat.presentation.di

import com.endocrine.chat.presentation.chat_list.ChatListViewModel
import com.endocrine.chat.presentation.chat_list_detail.ChatListDetailViewModel
import com.endocrine.chat.presentation.create_chat.CreateChatViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chatPresentationModule = module {
    viewModelOf(::ChatListViewModel)
    viewModelOf(::ChatListDetailViewModel)
    viewModelOf(::CreateChatViewModel)
}