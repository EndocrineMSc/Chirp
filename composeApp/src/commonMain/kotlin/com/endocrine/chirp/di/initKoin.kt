package com.endocrine.chirp.di

import com.endocrine.auth.presentation.di.authPresentationModule
import com.endocrine.chat.presentation.di.chatPresentationModule
import com.endocrine.core.data.di.coreDataModule
import com.endocrine.core.presentation.di.corePresentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            coreDataModule,
            authPresentationModule,
            chatPresentationModule,
            corePresentationModule
        )
    }
}