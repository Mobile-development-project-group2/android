
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.advancedandroidcourse.R
import com.example.advancedandroidcourse.model.teamDetails.Player
import com.example.advancedandroidcourse.model.teamDetails.RunningCompetition
import com.example.advancedandroidcourse.viewmodel.TeamDetailsState
import com.example.advancedandroidcourse.viewmodel.TeamDetailsViewModel




@Composable
fun TeamDetailsScreen(teamId: Int, navController: NavController,viewModel: TeamDetailsViewModel = hiltViewModel()) {


    LaunchedEffect(teamId) {
        viewModel.fetchTeamDetails(teamId)
    }
    val teamDetailsState by viewModel.teamDetailsState.collectAsState()



    when (teamDetailsState) {
        is TeamDetailsState.Loading -> {
            CircularProgressIndicator()
        }
        is TeamDetailsState.Success -> {
            // Display team details when data is successfully fetched
            val team = (teamDetailsState as TeamDetailsState.Success).teamDetails

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Team Logo
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(team.crest)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_launcher_foreground),
                        error = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "Team Logo",
                        contentScale = ContentScale.Inside,
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    // Column pushed to the right
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Row {
                            Text(text = team.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = " (${team.tla}) ")
                        }

                        Text(text = "Founded: ${team.founded}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "Venue: ${team.venue}", style = MaterialTheme.typography.bodySmall)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Column {
                    Text(text = "Coach", style = MaterialTheme.typography.titleMedium)
                    Text(text = "${team.coach.name}, ${team.coach.nationality}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Contract: ${team.coach.contract.start} - ${team.coach.contract.until}", style = MaterialTheme.typography.bodyMedium)
                }


                Spacer(modifier = Modifier.height(24.dp))
                RunningCompetitions(competitions = team.runningCompetitions)
                Spacer(modifier = Modifier.height(24.dp))

                Text(text = "Squad", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(8.dp))



                LazyColumn {
                    itemsIndexed(team.squad) { index, player ->
                        SquadItem(player = player, index = index + 1)
                    }
                }
            }
        }
        is TeamDetailsState.Error -> {
            val errorMessage = (teamDetailsState as TeamDetailsState.Error).message
            Text(text = "Error: $errorMessage", style = MaterialTheme.typography.bodyMedium)
        }
    }

}


@Composable
fun SquadItem(player: Player, index: Int) {

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$index - ${player.name}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(2f)
        )
        Text(
            text = player.position,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun RunningCompetitions(competitions: List<RunningCompetition>) {
    Text(text = "Running Competitions", style = MaterialTheme.typography.titleMedium)

    Spacer(modifier = Modifier.height(8.dp))

    // List of Running Competitions
    competitions.forEach { competition ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(competition.emblem)
                    .crossfade(true)
                    .build(),
                contentDescription = "Competition Emblem",
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "${competition.name} (${competition.code})",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
}



