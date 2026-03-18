package com.endocrine.auth.presentation.di

import com.endocrine.auth.presentation.register.RegisterViewModel
import com.endocrine.auth.presentation.register_success.RegisterSuccessViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::RegisterSuccessViewModel)
}