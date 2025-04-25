package com.example.advancedandroidcourse.navigation

import TeamDetailsScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.advancedandroidcourse.navigation.appbars.AppTopBar
import com.example.advancedandroidcourse.navigation.appbars.MatchesBottomBar
import com.example.advancedandroidcourse.navigation.appbars.MatchesTopBar
import com.example.advancedandroidcourse.presentation.auth.AuthViewModel
import com.example.advancedandroidcourse.ui.screens.LeaguesScreen
import com.example.advancedandroidcourse.ui.screens.LeaguesDetailsScreen
import com.example.advancedandroidcourse.ui.screens.LeaguesScreen
import com.example.advancedandroidcourse.presentation.auth.LoginScreen
import com.example.advancedandroidcourse.presentation.auth.RegisterScreen
import com.example.advancedandroidcourse.socket.LiveScoreScreen
import com.example.advancedandroidcourse.ui.screens.LiveMatchesScreen
import com.example.advancedandroidcourse.ui.screens.MatchesByDateScreen
import com.example.advancedandroidcourse.ui.screens.MatchesScreen
import com.example.advancedandroidcourse.ui.screens.UserProfileScreen

@Composable
fun AppNavigation(navController: NavHostController, startDestination: String, modifier: Modifier = Modifier) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Screens where you DON'T want to show the bars
    val hideBars = currentRoute in listOf("login", "register")

    Scaffold(
        topBar = {
            if (!hideBars) {
                MatchesTopBar()
            }
        },
        bottomBar = {
            if (!hideBars) {
                MatchesBottomBar(navController = navController)
            }
        },
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .padding(paddingValues)
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

            composable("live_score") {
                LiveScoreScreen(
                    navController = navController,

                )
            }

            composable("profile") {
                val viewModel: AuthViewModel = hiltViewModel()
                UserProfileScreen(navController = navController,
                    viewModel = viewModel)
            }

            composable("matches") {
                MatchesScreen(
                    navController = navController,
                    )
            }

            composable("live_matches") {
                LiveMatchesScreen(
                    navController = navController,
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
            composable("team_details/{teamId}") { backStackEntry ->
                val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull()
                teamId?.let { TeamDetailsScreen(it, navController) }
            }

            composable("matches_by_data"){


                MatchesByDateScreen(modifier = Modifier);
            }


        }
    }
}


