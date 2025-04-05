package com.example.advancedandroidcourse.presentation.auth.google_signin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
