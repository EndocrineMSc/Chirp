package com.endocrine.chat.data.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.endocrine.chat.data.chat.KtorChatParticipantService
import com.endocrine.chat.data.chat.KtorChatService
import com.endocrine.chat.database.DatabaseFactory
import com.endocrine.chat.domain.chat.ChatParticipantService
import com.endocrine.chat.domain.chat.ChatService
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformChatDataModule: Module

val chatDataModule = module {
    includes(platformChatDataModule)

    singleOf(::KtorChatParticipantService) bind ChatParticipantService::class
    singleOf(::KtorChatService) bind ChatService::class
    single {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}