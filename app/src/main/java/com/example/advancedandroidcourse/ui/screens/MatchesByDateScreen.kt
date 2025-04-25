package com.example.advancedandroidcourse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.advancedandroidcourse.model.matches.MatchByDate
import com.example.advancedandroidcourse.model.matches.MatchesByDateResponse
import com.example.advancedandroidcourse.model.matches.MatchesByDateUiState

import com.example.advancedandroidcourse.viewmodel.MatchesByDateViewModel

import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MatchesByDateScreen(
   viewModel: MatchesByDateViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {




    val uiState by viewModel.uiState.collectAsState()


    val selectedDate = remember { mutableStateOf(LocalDate.now()) }


    LaunchedEffect(selectedDate.value) {
        viewModel.fetchMatchesByDate(selectedDate.value.format(DateTimeFormatter.ISO_LOCAL_DATE))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        DateSelectionBar(
            currentDate = selectedDate.value,
            onDateSelected = {
                selectedDate.value = it
            }
        )

        Spacer(modifier = Modifier.height(16.dp))


        when (uiState) {
            is MatchesByDateUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is MatchesByDateUiState.Success -> {
                val matches = (uiState as MatchesByDateUiState.Success).matches
                MatchesList(matches = matches)
            }

            is MatchesByDateUiState.Error -> {
                val errorMessage = (uiState as MatchesByDateUiState.Error).message
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Error loading matches",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = errorMessage)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            viewModel.fetchMatchesByDate(selectedDate.value.format(DateTimeFormatter.ISO_LOCAL_DATE))
                        }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DateSelectionBar(
    currentDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {


    // Generate a list of 7 days (3 days before, current day, and 3 days after)
    val dateRange = (-3..3).map { currentDate.plusDays(it.toLong()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        dateRange.forEach { date ->
            val isSelected = date.equals(currentDate)

            DateChip(
                date = date,
                isSelected = isSelected,
                onClick = { onDateSelected(date) }
            )
        }
    }
}

@Composable
fun DateChip(
    date: LocalDate,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern("EEE\ndd")
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val textColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = Modifier
            .size(width = 50.dp, height = 60.dp)
            .background(
                color = backgroundColor,
                shape = MaterialTheme.shapes.medium
            )
            .padding(4.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date.format(formatter),
            color = textColor,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}


@Composable
fun MatchesList(matches: MatchesByDateResponse) {

    val leagues = listOf(
        "La Liga" to matches.`La Liga`,
        "Premier League" to matches.`Premier League`,
        "Ligue 1" to matches.`Ligue 1`,
        "Bundesliga" to matches.`Bundesliga`,
        "Serie A" to matches.`Serie A`
    )

    LazyColumn {
        leagues.forEach { (leagueName, leagueMatches) ->
            item {
                LeagueHeader(leagueName)
            }

            // Safe null check with Elvis operator
            if (leagueMatches?.isEmpty() != false) {
                item {
                    NoMatchesForLeague()
                }
            } else {
                items(leagueMatches) { match ->
                    MatchItem(match)
                }
            }
        }
    }
}

@Composable
fun LeagueHeader(leagueName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = leagueName,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun NoMatchesForLeague() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No matches scheduled",
            color = Color.Gray
        )
    }
}

@Composable
fun MatchItem(match: MatchByDate) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 1.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Teams
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = match.homeTeam,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = "vs",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = match.awayTeam,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(3f),
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Time
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = match.time,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
                )
            }
        }
    }
}

