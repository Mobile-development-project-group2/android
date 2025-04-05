package com.example.advancedandroidcourse.navigation

// AppNavigation.kt

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.advancedandroidcourse.navigation.appbars.AppTopBar
import com.example.advancedandroidcourse.ui.screens.LeaguesDetailsScreen
import com.example.advancedandroidcourse.ui.screens.LeaguesScreen
import com.example.advancedandroidcourse.presentation.auth.LoginScreen
import com.example.advancedandroidcourse.presentation.auth.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController, startDestination: String, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            AppTopBar(navController = navController, title = "FotScores")
        },
        modifier = modifier
    ) { paddingValues ->
        // Set the navigation graph with the start destination
        NavHost(
            navController = navController,
            startDestination = startDestination, // Correctl§y pass startDestination as a String
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("login") {
                LoginScreen(
                    navController = navController,  // Pass the navController here
                    onSuccess = {
                        navController.navigate("leagues") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }

            composable("register") {
                RegisterScreen(
                    navController = navController,
                    onSuccess = {
                        navController.navigate("leagues") {
                            popUpTo("register") { inclusive = true }
                        }
                    }
                )
            }

            composable("leagues") {
                LeaguesScreen(navController)
            }

            composable("league_details/{leagueCode}") { backStackEntry ->
                val leagueCode = backStackEntry.arguments?.getString("leagueCode")
                leagueCode?.let { LeaguesDetailsScreen(it, navController) }
            }
        }
    }
}


