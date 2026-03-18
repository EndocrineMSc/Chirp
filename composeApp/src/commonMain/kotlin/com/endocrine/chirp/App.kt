package com.endocrine.chirp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource

import chirp.composeapp.generated.resources.Res
import chirp.composeapp.generated.resources.compose_multiplatform
import com.endocrine.auth.presentation.register.RegisterRoot
import com.endocrine.auth.presentation.register_success.RegisterSuccessRoot
import com.endocrine.core.designsystem.components.brand.ChirpBrandLogo
import com.endocrine.core.designsystem.components.layouts.ChirpAdaptiveFormLayout
import com.endocrine.core.designsystem.theme.ChirpTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ChirpTheme {
        RegisterSuccessRoot()
    }
}