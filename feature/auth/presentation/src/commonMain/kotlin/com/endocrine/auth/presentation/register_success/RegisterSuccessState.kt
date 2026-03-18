package com.endocrine.auth.presentation.register_success

import com.endocrine.core.presentation.util.UiText

data class RegisterSuccessState(
    val registeredEmail: String = "",
    val isResendingVerificationEmail: Boolean = false,
    val resendVerificationError: UiText? = null
)
