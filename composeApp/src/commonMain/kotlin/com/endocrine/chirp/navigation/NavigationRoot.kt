package com.endocrine.chirp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.endocrine.auth.presentation.navigation.AuthGraphRoutes
import com.endocrine.auth.presentation.navigation.authGraph
import com.endocrine.chat.presentation.chat_list.ChatListRoute
import com.endocrine.chat.presentation.chat_list.ChatListScreeRoot
import kotlinx.serialization.Serializable

@Composable
fun NavigationRoot(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AuthGraphRoutes.Graph
    ) {
        authGraph(
            navController = navController,
            onLoginSuccess = {
                navController.navigate(ChatListRoute) {
                    popUpTo(AuthGraphRoutes.Graph) {
                        inclusive = true
                    }
                }
            }
        )
        composable<ChatListRoute> {
            ChatListScreeRoot()
        }
    }
}