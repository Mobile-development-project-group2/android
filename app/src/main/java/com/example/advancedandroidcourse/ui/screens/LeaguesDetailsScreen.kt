package com.example.advancedandroidcourse.ui.screens
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import coil3.compose.AsyncImage


@Composable
fun LeaguesDetailsScreen(leagueCode: String,navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header row for buttons
        Text(text = "Details for league : $leagueCode")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CompactButton(text = "Picture")
            CompactButton(text = "La Liga")
            CompactButton(text = "General View")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Metric display for M +/- (you can modify this as needed)
        Text(
            text = "M +/-",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.3f))
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))
        Header()


        // Looping through teams and displaying each in a row
        LazyColumn {
            items(teams){ team ->
                TeamStatsRow(team = team)
            }
        }
    }
}

@Composable
fun Header(){
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
            Text(
                text = "M W L P",
            )
        }
    }
}

@Composable
fun TeamStatsRow(team: Team) {
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
data class Team(
    val position: Int,
    val team: TeamDetails,
    val playedGames: Int,
    val form: String,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val points: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int
)

data class TeamDetails(
    val id: Int,
    val name: String,
    val shortName: String,
    val tla: String,
    val crest: String
)


val teams = listOf(
    Team(
        position = 1,
        team = TeamDetails(
            id = 81,
            name = "FC Barcelona",
            shortName = "Barça",
            tla = "FCB",
            crest = "https://crests.football-data.org/81.png"
        ),
        playedGames = 27,
        form = "null",
        won = 19,
        draw = 3,
        lost = 5,
        points = 60,
        goalsFor = 75,
        goalsAgainst = 27,
        goalDifference = 48
    ),
    Team(
        position = 2,
        team = TeamDetails(
            id = 86,
            name = "Real Madrid CF",
            shortName = "Real Madrid",
            tla = "RMA",
            crest = "https://crests.football-data.org/86.png"
        ),
        playedGames = 28,
        form = "null",
        won = 18,
        draw = 6,
        lost = 4,
        points = 60,
        goalsFor = 59,
        goalsAgainst = 27,
        goalDifference = 32
    ),
    Team(
        position = 3,
        team = TeamDetails(
            id = 78,
            name = "Club Atlético de Madrid",
            shortName = "Atleti",
            tla = "ATL",
            crest = "https://crests.football-data.org/78.png"
        ),
        playedGames = 28,
        form = "null",
        won = 16,
        draw = 8,
        lost = 4,
        points = 56,
        goalsFor = 46,
        goalsAgainst = 22,
        goalDifference = 24
    ),
    Team(
        position = 4,
        team = TeamDetails(
            id = 77,
            name = "Athletic Club",
            shortName = "Athletic",
            tla = "ATH",
            crest = "https://crests.football-data.org/77.png"
        ),
        playedGames = 28,
        form = "null",
        won = 14,
        draw = 10,
        lost = 4,
        points = 52,
        goalsFor = 46,
        goalsAgainst = 24,
        goalDifference = 22
    ),
    Team(
        position = 5,
        team = TeamDetails(
            id = 94,
            name = "Villarreal CF",
            shortName = "Villarreal",
            tla = "VIL",
            crest = "https://crests.football-data.org/94.png"
        ),
        playedGames = 27,
        form = "null",
        won = 12,
        draw = 8,
        lost = 7,
        points = 44,
        goalsFor = 49,
        goalsAgainst = 38,
        goalDifference = 11
    ),
    Team(
        position = 6,
        team = TeamDetails(
            id = 90,
            name = "Real Betis Balompié",
            shortName = "Real Betis",
            tla = "BET",
            crest = "https://crests.football-data.org/90.png"
        ),
        playedGames = 28,
        form = "null",
        won = 12,
        draw = 8,
        lost = 8,
        points = 44,
        goalsFor = 38,
        goalsAgainst = 35,
        goalDifference = 3
    ),
    Team(
        position = 7,
        team = TeamDetails(
            id = 89,
            name = "RCD Mallorca",
            shortName = "Mallorca",
            tla = "MAL",
            crest = "https://crests.football-data.org/89.png"
        ),
        playedGames = 28,
        form = "null",
        won = 11,
        draw = 7,
        lost = 10,
        points = 40,
        goalsFor = 28,
        goalsAgainst = 34,
        goalDifference = -6
    ),
    Team(
        position = 8,
        team = TeamDetails(
            id = 558,
            name = "RC Celta de Vigo",
            shortName = "Celta",
            tla = "CEL",
            crest = "https://crests.football-data.org/558.png"
        ),
        playedGames = 28,
        form = "null",
        won = 11,
        draw = 6,
        lost = 11,
        points = 39,
        goalsFor = 41,
        goalsAgainst = 41,
        goalDifference = 0
    ),
    Team(
        position = 9,
        team = TeamDetails(
            id = 87,
            name = "Rayo Vallecano de Madrid",
            shortName = "Rayo Vallecano",
            tla = "RAY",
            crest = "https://crests.football-data.org/87.png"
        ),
        playedGames = 28,
        form = "null",
        won = 9,
        draw = 10,
        lost = 9,
        points = 37,
        goalsFor = 31,
        goalsAgainst = 31,
        goalDifference = 0
    ),
    Team(
        position = 10,
        team = TeamDetails(
            id = 559,
            name = "Sevilla FC",
            shortName = "Sevilla FC",
            tla = "SEV",
            crest = "https://crests.football-data.org/559.png"
        ),
        playedGames = 28,
        form = "null",
        won = 9,
        draw = 9,
        lost = 10,
        points = 36,
        goalsFor = 32,
        goalsAgainst = 37,
        goalDifference = -5
    ),
    Team(
        position = 11,
        team = TeamDetails(
            id = 82,
            name = "Getafe CF",
            shortName = "Getafe",
            tla = "GET",
            crest = "https://crests.football-data.org/82.png"
        ),
        playedGames = 28,
        form = "null",
        won = 9,
        draw = 9,
        lost = 10,
        points = 36,
        goalsFor = 25,
        goalsAgainst = 23,
        goalDifference = 2
    ),
    Team(
        position = 12,
        team = TeamDetails(
            id = 92,
            name = "Real Sociedad de Fútbol",
            shortName = "Real Sociedad",
            tla = "RSO",
            crest = "https://crests.football-data.org/92.png"
        ),
        playedGames = 28,
        form = "null",
        won = 10,
        draw = 5,
        lost = 13,
        points = 35,
        goalsFor = 25,
        goalsAgainst = 30,
        goalDifference = -5
    ),
    Team(
        position = 13,
        team = TeamDetails(
            id = 298,
            name = "Girona FC",
            shortName = "Girona",
            tla = "GIR",
            crest = "https://crests.football-data.org/298.png"
        ),
        playedGames = 28,
        form = "null",
        won = 9,
        draw = 7,
        lost = 12,
        points = 34,
        goalsFor = 36,
        goalsAgainst = 41,
        goalDifference = -5
    ),
    Team(
        position = 14,
        team = TeamDetails(
            id = 79,
            name = "CA Osasuna",
            shortName = "Osasuna",
            tla = "OSA",
            crest = "https://crests.football-data.org/79.png"
        ),
        playedGames = 27,
        form = "null",
        won = 7,
        draw = 12,
        lost = 8,
        points = 33,
        goalsFor = 33,
        goalsAgainst = 39,
        goalDifference = -6
    ),
    Team(
        position = 15,
        team = TeamDetails(
            id = 80,
            name = "RCD Espanyol de Barcelona",
            shortName = "Espanyol",
            tla = "ESP",
            crest = "https://crests.football-data.org/80.png"
        ),
        playedGames = 27,
        form = "null",
        won = 7,
        draw = 7,
        lost = 13,
        points = 28,
        goalsFor = 26,
        goalsAgainst = 39,
        goalDifference = -13
    ),
    Team(
        position = 16,
        team = TeamDetails(
            id = 95,
            name = "Valencia CF",
            shortName = "Valencia",
            tla = "VAL",
            crest = "https://crests.football-data.org/95.png"
        ),
        playedGames = 28,
        form = "null",
        won = 6,
        draw = 10,
        lost = 12,
        points = 28,
        goalsFor = 31,
        goalsAgainst = 46,
        goalDifference = -15
    ),
    Team(
        position = 17,
        team = TeamDetails(
            id = 263,
            name = "Deportivo Alavés",
            shortName = "Alavés",
            tla = "ALA",
            crest = "https://crests.football-data.org/263.png"
        ),
        playedGames = 28,
        form = "null",
        won = 6,
        draw = 9,
        lost = 13,
        points = 27,
        goalsFor = 32,
        goalsAgainst = 42,
        goalDifference = -10
    ),
    Team(
        position = 18,
        team = TeamDetails(
            id = 745,
            name = "CD Leganés",
            shortName = "Leganés",
            tla = "LEG",
            crest = "https://crests.football-data.org/745.png"
        ),
        playedGames = 28,
        form = "null",
        won = 6,
        draw = 9,
        lost = 13,
        points = 27,
        goalsFor = 26,
        goalsAgainst = 43,
        goalDifference = -17
    )
)