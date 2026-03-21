package com.endocrine.chirp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.endocrine.auth.presentation.navigation.AuthGraphRoutes
import com.endocrine.chat.presentation.chat_list.ChatListRoute
import com.endocrine.chirp.navigation.DeepLinkListener
import com.endocrine.chirp.navigation.NavigationRoot
import com.endocrine.core.designsystem.theme.ChirpTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    onAuthenticationChecked: () -> Unit = {},
    viewModel: MainViewModel = koinViewModel()
) {
    val navController = rememberNavController()
    DeepLinkListener(navController)

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isCheckingAuth) {
        if(!state.isCheckingAuth) {
            onAuthenticationChecked()
        }
    }

    ChirpTheme {
        if (!state.isCheckingAuth) {
            NavigationRoot(
                navController,
                startDestination = if(state.isLoggedIn) {
                    ChatListRoute
                } else {
                    AuthGraphRoutes.Graph
                }
            )
        }
    }
}