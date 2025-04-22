package com.example.advancedandroidcourse.navigation.appbars


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Top Bar for the Matches Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesTopBar() {
    TopAppBar(
        title = { Text("LIVESCORE") },
        actions = {
            // Search (src) Icon on the right side
            IconButton(onClick = { /* Handle Search Icon click */ }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
            // Time Logo
            IconButton(onClick = { /* Do something */ }) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "Time Logo")
            }
            // Calendar Icon
            IconButton(onClick = { /* Do something */ }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Calendar")


            }
        }
    )
}

// Bottom Navigation for the Matches Screen
@Composable
fun MatchesBottomBar() {
    BottomAppBar(
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Matches Icon
            IconButton(onClick = { /* Handle Matches */ }) {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Matches")
            }
            // Leagues Icon
            IconButton(onClick = { /* Handle Leagues */ }) {
                Icon(imageVector = Icons.Default.Star, contentDescription = "Leagues")
            }
            // News Icon
            IconButton(onClick = { /* Handle News */ }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "News")
            }
            // Following Icon
            IconButton(onClick = { /* Handle Following */ }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Following")
            }
            // More Icon
            IconButton(onClick = { /* Handle More */ }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
        }
    }
}