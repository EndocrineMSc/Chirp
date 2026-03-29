package com.endocrine.chat.data.di

import com.endocrine.chat.data.chat.KtorChatParticipantService
import com.endocrine.chat.data.chat.KtorChatService
import com.endocrine.chat.domain.chat.ChatParticipantService
import com.endocrine.chat.domain.chat.ChatService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val chatDataModule = module {
    singleOf(::KtorChatParticipantService) bind ChatParticipantService::class
    singleOf(::KtorChatService) bind ChatService::class
}