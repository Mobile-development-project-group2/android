package com.example.advancedandroidcourse.ui.screens


import Match
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LiveMatchesScreen(
    navController: NavController,
    matches: List<Match>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Live Matches",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn {
            items(matches) { match ->
                MatchRow(match = match) {
                    navController.navigate("liveScore/${match.id}") // Assuming `match.id` exists
                }
            }
        }
    }
}

@Composable
fun MatchRow(match: Match, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${match.homeTeam} - ${match.awayTeam}",
                fontSize = 18.sp
            )
            Text(
                text = "${match.minute}'",
                fontSize = 16.sp
            )
        }
    }
}
