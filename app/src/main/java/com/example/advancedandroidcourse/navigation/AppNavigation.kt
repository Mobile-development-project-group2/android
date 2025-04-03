package com.example.advancedandroidcourse.navigation

import TeamDetailsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.advancedandroidcourse.ui.screens.LeaguesScreen
import com.example.advancedandroidcourse.ui.screens.LeaguesDetailsScreen

//Navigation with Jetpack Navigation Component

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier){
    NavHost(
        navController=navController,
        startDestination = "team_details",
        modifier =modifier
    ){
        composable("leagues") { LeaguesScreen(navController) }
        composable("league_details/{leagueCode}") { backStackEntry ->
            val leagueCode = backStackEntry.arguments?.getString("leagueCode")?.toString()
            leagueCode?.let { LeaguesDetailsScreen(it) }
        }
        composable("team_details") { TeamDetailsScreen() }

    }
}

