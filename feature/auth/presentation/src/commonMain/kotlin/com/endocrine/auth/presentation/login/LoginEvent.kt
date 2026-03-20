package com.endocrine.auth.presentation.login

sealed interface LoginEvent {
    data object Success : LoginEvent
}
