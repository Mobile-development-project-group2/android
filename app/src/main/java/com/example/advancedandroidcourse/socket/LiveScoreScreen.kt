package com.example.advancedandroidcourse.socket

import GoalEvent
import LiveScoreViewModel
import Match
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun LiveScoreScreen(
    navController: NavController,
    viewModel: LiveScoreViewModel = viewModel()
) {
    val match by viewModel.matchState.collectAsState()
    val goals by viewModel.goals.collectAsState()
    val isConnected by viewModel.isConnected.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title and connection status
        Text(
            text = "Live Match",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        if (!isConnected) {
            Text(
                text = "Connecting to server...",
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        // Teams and Score
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "${match.homeTeam} vs ${match.awayTeam}",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = match.homeScore.toString(),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = " - ",
                fontSize = 36.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = match.awayScore.toString(),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Minute: ${match.minute}'",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        // Goals list
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Match Events",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(goals) { goal ->
                GoalEventItem(goal = goal, match = match)
            }
        }
    }
}

@Composable
fun GoalEventItem(goal: GoalEvent, match: Match) {
    val isHomeGoal = goal.team == "HOME"
    val team = if (isHomeGoal) match.homeTeam else match.awayTeam

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = if (isHomeGoal) Arrangement.Start else Arrangement.End
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 220.dp) // Limit width to half screen or less
                .background(Color.White),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "⚽",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = goal.scorer,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$team - ${goal.minute}'",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}