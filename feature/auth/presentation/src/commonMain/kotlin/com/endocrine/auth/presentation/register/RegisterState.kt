package com.endocrine.auth.presentation.register

import androidx.compose.foundation.text.input.TextFieldState
import com.endocrine.core.presentation.util.UiText

data class RegisterState(
    val emailTextState: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val emailError: UiText? = null,
    val passwordTextState: TextFieldState = TextFieldState(),
    val passwordValid: Boolean = false,
    val passwordError: UiText? = null,
    val isPasswordVisible: Boolean = false,
    val userNameTextState: TextFieldState = TextFieldState(),
    val userNameValid: Boolean = false,
    val userNameError: UiText? = null,
    val registrationError: UiText? = null,
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false
)