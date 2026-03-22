package com.endocrine.auth.presentation.di

import com.endocrine.auth.presentation.email_verification.EmailVerificationViewModel
import com.endocrine.auth.presentation.forgot_password.ForgotPasswordViewModel
import com.endocrine.auth.presentation.login.LoginViewModel
import com.endocrine.auth.presentation.register.RegisterViewModel
import com.endocrine.auth.presentation.register_success.RegisterSuccessViewModel
import com.endocrine.auth.presentation.reset_password.ResetPasswordViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authPresentationModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::RegisterSuccessViewModel)
    viewModelOf(::EmailVerificationViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::ForgotPasswordViewModel)
    viewModelOf(::ResetPasswordViewModel)
}