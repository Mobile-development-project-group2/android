package com.example.advancedandroidcourse.navigation

// AppNavigation.kt

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.advancedandroidcourse.navigation.appbars.AppTopBar
import com.example.advancedandroidcourse.ui.screens.LeaguesDetailsScreen
import com.example.advancedandroidcourse.ui.screens.LeaguesScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            AppTopBar(navController = navController, title = "FotScores")
        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "leagues",
            modifier = Modifier.padding(paddingValues)
        ) {
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

