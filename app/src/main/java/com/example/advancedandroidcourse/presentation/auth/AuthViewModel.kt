package com.example.advancedandroidcourse.presentation.auth

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    var state by mutableStateOf(AuthState())
        private set

    // Handle email change
    fun onEmailChange(email: String) {
        state = state.copy(email = email)
    }

    // Handle password change
    fun onPasswordChange(password: String) {
        state = state.copy(password = password)
    }

    // Handle confirm password change (for register)
    fun onConfirmPasswordChange(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }

    // Handle login logic
    fun signInWithEmailAndPassword() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, authError = null)

            firebaseAuth.signInWithEmailAndPassword(state.email, state.password)
                .addOnCompleteListener { task ->
                    state = if (task.isSuccessful) {
                        state.copy(isLoading = false, authSuccess = true)
                    } else {
                        state.copy(
                            isLoading = false,
                            authError = task.exception?.message
                        )
                    }
                }
        }
    }

    // Handle register logic
    fun registerWithEmailAndPassword() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, authError = null)

            if (state.password != state.confirmPassword) {
                state = state.copy(
                    isLoading = false,
                    authError = "Passwords do not match"
                )
                return@launch
            }

            firebaseAuth.createUserWithEmailAndPassword(state.email, state.password)
                .addOnCompleteListener { task ->
                    state = if (task.isSuccessful) {
                        state.copy(isLoading = false, authSuccess = true)
                    } else {
                        state.copy(
                            isLoading = false,
                            authError = task.exception?.message
                        )
                    }
                }
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        state = AuthState() // Resets to default values
    }
}




