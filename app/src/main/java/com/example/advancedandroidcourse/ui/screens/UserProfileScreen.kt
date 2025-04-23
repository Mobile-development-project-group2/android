package com.example.advancedandroidcourse.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.advancedandroidcourse.presentation.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.example.advancedandroidcourse.presentation.auth.LogoutButton

@Composable
fun UserProfileScreen(navController: NavController, viewModel: AuthViewModel) {
    val user = FirebaseAuth.getInstance().currentUser
    val email = user?.email ?: "No email available"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "User Profile", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Email: $email", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(32.dp))
        LogoutButton(viewModel = viewModel, navController = navController)
    }
}