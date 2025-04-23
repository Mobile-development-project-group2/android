package com.example.advancedandroidcourse.presentation.auth

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun LogoutButton(viewModel: AuthViewModel, navController: NavController) {
    Button(onClick = { viewModel.logout() }) {
        Button(onClick = {
            viewModel.logout()
            navController.navigate("login") {
                popUpTo(0) { inclusive = true } // Remove the profile screen from the backstack
            }
        }) {
            Text("Logout")
        }
    }
}