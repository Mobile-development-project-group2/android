package com.example.advancedandroidcourse

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.advancedandroidcourse.navigation.AppNavigation
import com.example.advancedandroidcourse.ui.theme.AdvancedAndroidCourseTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AdvancedAndroidCourseTheme {
                // Initialize the NavController using rememberNavController
                val navController = rememberNavController()
                var startDestination by remember { mutableStateOf("leagues") }

                val systemUiController = rememberSystemUiController()
                val useDarkIcons = true

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.White,
                        darkIcons = useDarkIcons
                    )
                }

                LaunchedEffect(Unit) {
                    // Delay the authentication check until Firebase is initialized
                    if (firebaseAuth.currentUser != null) {
                        startDestination = "leagues"
                    } else {
                        startDestination = "login"
                    }
                }

                // Pass the startDestination to AppNavigation
                startDestination?.let {
                    AppNavigation(navController = navController, startDestination = it)
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 101)
                }

            }
        }
    }
}



