package com.example.advancedandroidcourse.ui.screens

import Standing
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.advancedandroidcourse.viewmodel.LeagueDetailsViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.advancedandroidcourse.R
import com.example.advancedandroidcourse.viewmodel.LeagueDetailsState


@Composable
fun LeaguesDetailsScreen(leagueCode: String, navController: NavController) {

    val viewModel: LeagueDetailsViewModel = hiltViewModel()
    val leagueDetailsState by viewModel.leagueDetailsState.collectAsState()
    val context = LocalContext.current


    // Trigger data loading
    LaunchedEffect(leagueCode) {
        viewModel.fetchLeagueDetails(leagueCode)
    }

    // UI handling based on the state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(leagueDetailsState) {
            is LeagueDetailsState.Loading -> {
                Text("Loading...", modifier = Modifier.padding(16.dp))
            }
            is LeagueDetailsState.Error -> {
                Text("Error fetching league details", modifier = Modifier.padding(16.dp))
            }
            is LeagueDetailsState.Success -> {
                // Show the league details when successful
                val leagueDetails = (leagueDetailsState as LeagueDetailsState.Success).leagueDetails


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(leagueDetails.competition.emblem)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        error = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "League Logo",
                        contentScale = ContentScale.Fit, // or Inside depending on your preference
                        modifier = Modifier
                            .size(64.dp) // you can adjust this to your desired size
                            .weight(1f) // makes image occupy half the space
                    )

                    // Text buttons on the right
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                       Text(text = leagueDetails.competition.name)
                       Text(text = leagueDetails.area.name)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Metric display for M +/- (you can modify this as needed)
                Text(
                    text = "Table",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray.copy(alpha = 0.3f))
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))
                Header()

                // Loop through teams and display each in a row
                LazyColumn {
                    items(leagueDetails.standings[0].table) { team ->
                        TeamStatsRow(team = team,navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.1f))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Team crest and name
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Show the team crest
            Text("Team")
            Spacer(modifier = Modifier.width(8.dp))
        }

        // Team statistics: played, won, lost, points
        Row(horizontalArrangement = Arrangement.End) {
            // Team statistics
            Text(text = "M W L P")
        }
    }
}

@Composable
fun TeamStatsRow(team: Standing,navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.1f))
            .padding(8.dp)
            .clickable {
                navController.navigate("team_details/${team.team.id}")
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Team crest and name
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Show the team crest
            AsyncImage(
                model = team.team.crest,
                contentDescription = "Team Crest",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = team.team.shortName)
        }

        // Team statistics: played, won, lost, points
        Row(horizontalArrangement = Arrangement.End) {
            // Team statistics
            Text(
                text = "${team.playedGames} ${team.won} ${team.lost} ${team.points}",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun CompactButton(text: String) {
    Button(
        onClick = { /* Handle click */ },
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(text)
    }
}



