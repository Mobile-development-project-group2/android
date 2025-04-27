package com.example.advancedandroidcourse.navigation.appbars


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Top Bar for the Matches Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesTopBar() {
    TopAppBar(
        title = {
            Text(
                "LIVESCORE",
                color = Color.White // Make the title text white
            )
        },
        actions = {
            IconButton(onClick = { /* Handle Time Logo */ }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Time Logo",
                    tint = Color.White
                )
            }
            IconButton(onClick = { /* Handle Calendar Icon */ }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Calendar",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary, // Deep blue background (or pick any color)
            titleContentColor = Color.White,   // Optional if you set color in Text()
            actionIconContentColor = Color.White
        )
    )
}

// Bottom Navigation for the Matches Screen
@Composable
fun MatchesBottomBar(navController: NavController) {
    BottomAppBar(
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Matches Icon with Text
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = { navController.navigate("matches_by_data") }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Matches")
                }
                Text(text = "Matches", style = MaterialTheme.typography.labelSmall)
            }

            // Leagues Icon with Text
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = { navController.navigate("leagues") }) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "Leagues")
                }
                Text(text = "Leagues", style = MaterialTheme.typography.labelSmall)
            }

            // Live Match Icon with Text
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = { navController.navigate("live_matches") }) {
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Live Matches")
                }
                Text(text = "Live Matches", style = MaterialTheme.typography.labelSmall)
            }

            // User Profile Icon with Text
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = { navController.navigate("profile") }) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Profile")
                }
                Text(text = "Profile", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}