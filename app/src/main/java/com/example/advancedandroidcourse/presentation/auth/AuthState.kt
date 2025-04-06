package com.example.advancedandroidcourse.presentation.auth

//Screens related to authentication

data class AuthState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val authSuccess: Boolean = false,
    val authError: String? = null
)