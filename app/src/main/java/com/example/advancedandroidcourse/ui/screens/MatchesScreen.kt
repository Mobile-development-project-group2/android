package com.example.advancedandroidcourse.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import java.time.LocalDate as LocalDate1


@Composable
fun MatchesScreen(navController: NavController, modifier: Modifier = Modifier) {
    var selectedDate by remember { mutableStateOf(LocalDate1.now()) }
    var showAll by remember { mutableStateOf(false) }

    // Sample match data
    val allMatches = listOf(
        Match(
            matchDate = "2025-04-08",
            matchTime = "18:00",
            homeTeam = "Team A",
            awayTeam = "Team B",
            homeScore = 2,
            awayScore = 1,
            isLive = true,
            homeLogo = "home_logo_url",
            awayLogo = "away_logo_url",
            leagueName = "Premier League"
        ),
        Match(
            matchDate = "2025-04-08",
            matchTime = "20:00",
            homeTeam = "Team C",
            awayTeam = "Team D",
            homeScore = 3,
            awayScore = 1,
            isLive = false,
            homeLogo = "home_logo_url",
            awayLogo = "away_logo_url",
            leagueName = "Premier League"
        ),
    )

    // Filter matches based on selected date
    val matchesForDate = allMatches.filter { it.matchDate == selectedDate.toString() }

    // Handle Show All / Hide All
    val displayedMatches = if (showAll) allMatches else matchesForDate

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // or any padding you want
    ) {
        DateSelector(
            currentDate = selectedDate,
            onDateClick = { selectedDate = it }
        )

        LazyColumn(
            modifier = Modifier.weight(1f) // Take available space
        ) {
            items(displayedMatches) { match ->
                MatchCard(match = match)
            }
        }

        ShowHideMatches(
            showAll = showAll,
            onToggle = { showAll = !showAll }
        )
    }
}










@Composable
fun ShowHideMatches(showAll: Boolean, onToggle: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onToggle) {
            Text(if (showAll) "Hide All" else "Show All")
        }
    }
}


// Date Selector Composable
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateSelector(
    currentDate: LocalDate1,
    onDateClick: (LocalDate1) -> Unit
) {
    // Define the range of dates to display, e.g., from -3 days to +3 days from today
    val rangeStart = currentDate.minusDays(3)
    val rangeEnd = currentDate.plusDays(3)

    val dateRange = generateSequence(rangeStart) { it.plusDays(1) }
        .takeWhile { !it.isAfter(rangeEnd) }
        .toList()

    // Horizontal Scrolling for Date Selector
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        // Date Buttons: Yesterday, Today, Tomorrow
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { onDateClick(currentDate.minusDays(1)) }) {
                Text("Yesterday")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { onDateClick(LocalDate1.now()) }) {
                Text("Today")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { onDateClick(currentDate.plusDays(1)) }) {
                Text("Tomorrow")
            }
        }

        // Horizontal Scrolling Row for Dates in the Range
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dateRange) { date ->
                Text(
                    text = date.dayOfMonth.toString(),
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { onDateClick(date) }
                        .background(
                            if (date.isEqual(currentDate)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                        )
                        .padding(12.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (date.isEqual(currentDate)) Color.White else Color.Black
                )
            }
        }

    }
}

// Match Card Composable
@Composable
fun MatchCard(match: Match) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "match.: ${match.matchDate}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            // Add League Name
            Text(
                text = "League: ${match.leagueName}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Load Home Logo Image using Coil
                LoadImage(match.homeLogo)
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = match.homeTeam, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.width(16.dp))
                // Center Match Time
                Text(
                    text =  match.matchTime,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = match.awayTeam, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.width(8.dp))

                // Load Away Logo Image using Coil
                LoadImage(match.awayLogo)
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (match.isLive) {
                Text(text = "LIVE", color = Color.Red, style = MaterialTheme.typography.bodySmall)
            } else {
                Text(text = "Upcoming", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// Match Data Model (replace with actual data)
data class Match(
    val matchDate: String,
    val matchTime: String, // Add time for the match
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int,
    val awayScore: Int,
    val isLive: Boolean,
    val leagueName: String,
    val homeLogo: String,
    val awayLogo: String
)

@Composable
fun LoadImage(url: String) {
    val painter = rememberAsyncImagePainter(url)
    Image(painter = painter, contentDescription = "Team Logo")
}


