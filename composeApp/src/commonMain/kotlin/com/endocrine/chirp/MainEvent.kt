package com.endocrine.chirp

sealed interface MainEvent {
    data object OnSessionExpired: MainEvent
}