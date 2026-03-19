package com.endocrine.chirp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.endocrine.chirp.navigation.DeepLinkListener
import com.endocrine.chirp.navigation.NavigationRoot
import com.endocrine.core.designsystem.theme.ChirpTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    DeepLinkListener(navController)

    ChirpTheme {
        NavigationRoot(navController)
    }
}