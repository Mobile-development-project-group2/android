package com.example.advancedandroidcourse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.advancedandroidcourse.navigation.AppNavigation
import com.example.advancedandroidcourse.ui.theme.AdvancedAndroidCourseTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedAndroidCourseTheme {
                // Initialize the NavController using rememberNavController
                val navController = rememberNavController()

                var startDestination by remember { mutableStateOf("live_score") } // Default to "login"
                firebaseAuth.signOut()
                // Use LaunchedEffect to execute code after the initial composition


                /*
                LaunchedEffect(Unit) {
                    // Delay the authentication check until Firebase is initialized
                    if (firebaseAuth.currentUser != null) {
                        startDestination = "leagues"
                    } else {
                        startDestination = "login"
                    }
                }
                */





                // Pass the startDestination to AppNavigation
                AppNavigation(navController = navController, startDestination = startDestination)
            }
        }
    }
}


