package com.example.advancedandroidcourse.navigation

import TeamDetailsScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.advancedandroidcourse.navigation.appbars.AppTopBar
import com.example.advancedandroidcourse.ui.screens.LeaguesScreen
import com.example.advancedandroidcourse.ui.screens.LeaguesDetailsScreen

//Navigation with Jetpack Navigation Component

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    // Use Scaffold to include TopAppBar globally
    Scaffold(
        topBar = {
            // Here, we display the AppTopBar at the top of each screen
            AppTopBar(navController = navController, title = "FotScores")
        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "leagues",
            modifier = Modifier.padding(paddingValues)  // Apply padding for Scaffold content
        ) {
            composable("leagues") {
                LeaguesScreen(navController)
            }
            composable("league_details/{leagueCode}") { backStackEntry ->
                val leagueCode = backStackEntry.arguments?.getString("leagueCode")
                leagueCode?.let { LeaguesDetailsScreen(it, navController) }
            }
            composable("team_details/{teamId}") { backStackEntry ->
                val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                teamId?.let { TeamDetailsScreen(it, navController) }
            }
        }
    }
}

