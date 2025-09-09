package com.example.contacts.viewmodel

import android.os.Message
import com.example.contacts.data.User

sealed interface UiState {
    data object Loading : UiState
    data class Success(val users: List<User>) : UiState
    data class Error(val message: String) : UiState
}