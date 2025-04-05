package com.example.advancedandroidcourse

// MainActivity.kt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.advancedandroidcourse.ui.theme.AdvancedAndroidCourseTheme
import com.google.firebase.auth.FirebaseAuth
import com.example.advancedandroidcourse.navigation.AppNavigation
import com.example.advancedandroidcourse.presentation.auth.LoginScreen
import com.example.advancedandroidcourse.presentation.auth.RegisterScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedAndroidCourseTheme {
                val navController = rememberNavController()

                // Check if user is already signed in
                val startDestination = if (firebaseAuth.currentUser != null) "leagues" else "login"

                NavHost(
                    navController = navController,
                    startDestination = startDestination
                ) {
                    composable("login") {
                        LoginScreen(
                            onSuccess = {
                                navController.navigate("leagues") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("register") {
                        RegisterScreen(
                            onSuccess = {
                                navController.navigate("leagues") {
                                    popUpTo("register") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("leagues") {
                        AppNavigation(navController)
                    }
                }
            }
        }
    }
}


