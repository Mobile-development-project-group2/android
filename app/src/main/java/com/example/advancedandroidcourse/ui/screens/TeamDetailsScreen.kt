
import android.util.Log
import androidx.benchmark.perfetto.Row
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.advancedandroidcourse.R
import com.google.gson.Gson
import java.text.NumberFormat
import java.util.Locale


// Define data classes for JSON parsing
data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    val tla: String,
    val crest: String,
    val address: String,
    val website: String,
    val founded: Int,
    val clubColors: String,
    val venue: String,
    val area: Area,
    val runningCompetitions: List<Competition>,
    val coach: Coach,
    val marketValue: Long,
    val squad: List<Player>
)

data class Area(
    val id: Int,
    val name: String,
    val code: String,
    val flag: String
)

data class Competition(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String?
)

data class Coach(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val name: String,
    val dateOfBirth: String,
    val nationality: String,
    val contract: Contract
)

data class Contract(
    val start: String,
    val until: String
)

data class Player(
    val id: Int,
    val firstName: String?,
    val lastName: String,
    val name: String,
    val position: String,
    val dateOfBirth: String,
    val nationality: String,
    val shirtNumber: Int,
    val marketValue: Long?,
    val contract: Contract
)


@Composable
fun TeamDetailsScreen() {
    val jsonString = """
    {
        "area": {
            "id": 2224,
            "name": "Spain",
            "code": "ESP",
            "flag": "https://crests.football-data.org/760.svg"
        },
        "id": 90,
        "name": "Real Betis Balompié",
        "shortName": "Real Betis",
        "tla": "BET",
        "crest": "https://crests.football-data.org/90.png",
        "address": "Avenida de Heliópolis, s/n Sevilla 41012",
        "website": "http://www.realbetisbalompie.es",
        "founded": 1907,
        "clubColors": "Green / White",
        "venue": "Estadio Benito Villamarín",
        "runningCompetitions": [
            {
                "id": 2014,
                "name": "Primera Division",
                "code": "PD",
                "type": "LEAGUE",
                "emblem": "https://crests.football-data.org/PD.png"
            },
            {
                "id": 2146,
                "name": "UEFA Europa League",
                "code": "EL",
                "type": "CUP",
                "emblem": "https://crests.football-data.org/EL.png"
            },
            {
                "id": 2079,
                "name": "Copa del Rey",
                "code": "CDR",
                "type": "CUP",
                "emblem": null
            }
        ],
        "coach": {
            "id": 11630,
            "firstName": "Manuel",
            "lastName": "Pellegrini",
            "name": "Manuel Pellegrini",
            "dateOfBirth": "1953-09-16",
            "nationality": "Chile",
            "contract": {
                "start": "2020-08",
                "until": "2023-06"
            }
        },
        "marketValue": 225100000,
        "squad": [
            {
                "id": 7821,
                "firstName": "",
                "lastName": "Joel",
                "name": "Joel Robles",
                "position": "Goalkeeper",
                "dateOfBirth": "1990-06-17",
                "nationality": "Spain",
                "shirtNumber": 1,
                "marketValue": 2000000,
                "contract": {
                    "start": "2018-07",
                    "until": "2022-06"
                }
            },
            {
                "id": 7879,
                "firstName": "Claudio",
                "lastName": "Bravo",
                "name": "Claudio Bravo",
                "position": "Goalkeeper",
                "dateOfBirth": "1983-04-13",
                "nationality": "Chile",
                "shirtNumber": 25,
                "marketValue": 900000,
                "contract": {
                    "start": "2020-08",
                    "until": "2023-06"
                }
            },
            {
                "id": 32014,
                "firstName": "",
                "lastName": "Rui Silva",
                "name": "Rui Silva",
                "position": "Goalkeeper",
                "dateOfBirth": "1994-02-07",
                "nationality": "Portugal",
                "shirtNumber": 13,
                "marketValue": 14000000,
                "contract": {
                    "start": "2021-07",
                    "until": "2026-06"
                }
            },
            {
                "id": 33035,
                "firstName": "",
                "lastName": "Dani Rebollo",
                "name": "Dani Rebollo",
                "position": "Goalkeeper",
                "dateOfBirth": "1999-12-10",
                "nationality": "Spain",
                "shirtNumber": 30,
                "marketValue": null,
                "contract": {
                    "start": "2021-09",
                    "until": "2022-06"
                }
            },
            {
                "id": 16,
                "firstName": "Andrés",
                "lastName": "Guardado",
                "name": "Andrés Guardado",
                "position": "Defence",
                "dateOfBirth": "1986-09-28",
                "nationality": "Mexico",
                "shirtNumber": 18,
                "marketValue": 1500000,
                "contract": {
                    "start": "2017-07",
                    "until": "2023-06"
                }
            },
            {
                "id": 1772,
                "firstName": "Germán",
                "lastName": "Pezzella",
                "name": "Germán Pezzella",
                "position": "Defence",
                "dateOfBirth": "1991-06-27",
                "nationality": "Argentina",
                "shirtNumber": 16,
                "marketValue": 5000000,
                "contract": {
                    "start": "2021-08",
                    "until": "2025-06"
                }
            },
            {
                "id": 3624,
                "firstName": "Youssouf",
                "lastName": "Sabaly",
                "name": "Youssouf Sabaly",
                "position": "Defence",
                "dateOfBirth": "1993-03-05",
                "nationality": "Senegal",
                "shirtNumber": 23,
                "marketValue": 3000000,
                "contract": {
                    "start": "2021-07",
                    "until": "2026-06"
                }
            },
            {
                "id": 7783,
                "firstName": "",
                "lastName": "Héctor Bellerín",
                "name": "Héctor Bellerín",
                "position": "Defence",
                "dateOfBirth": "1995-03-19",
                "nationality": "Spain",
                "shirtNumber": 19,
                "marketValue": 20000000,
                "contract": {
                    "start": "2021-08",
                    "until": "2022-06"
                }
            },
            {
                "id": 32123,
                "firstName": "",
                "lastName": "Álex Moreno",
                "name": "Alex Moreno",
                "position": "Defence",
                "dateOfBirth": "1993-06-08",
                "nationality": "Spain",
                "shirtNumber": 15,
                "marketValue": 10000000,
                "contract": {
                    "start": "2019-08",
                    "until": "2025-06"
                }
            },
            {
                "id": 32491,
                "firstName": "",
                "lastName": "Juan Miranda",
                "name": "Juan Miranda",
                "position": "Defence",
                "dateOfBirth": "2000-01-19",
                "nationality": "Spain",
                "shirtNumber": 33,
                "marketValue": 6000000,
                "contract": {
                    "start": "2020-10",
                    "until": "2024-06"
                }
            },
            {
                "id": 33038,
                "firstName": "",
                "lastName": "Bartra",
                "name": "Bartra",
                "position": "Defence",
                "dateOfBirth": "1991-01-15",
                "nationality": "Spain",
                "shirtNumber": 5,
                "marketValue": 6000000,
                "contract": {
                    "start": "2018-07",
                    "until": "2023-06"
                }
            },
            {
                "id": 33106,
                "firstName": "",
                "lastName": "Víctor Ruiz",
                "name": "Víctor Ruíz",
                "position": "Defence",
                "dateOfBirth": "1989-01-25",
                "nationality": "Spain",
                "shirtNumber": 6,
                "marketValue": 2000000,
                "contract": {
                    "start": "2020-08",
                    "until": "2023-06"
                }
            },
            {
                "id": 33139,
                "firstName": "",
                "lastName": "Martín Montoya",
                "name": "Martín Montoya",
                "position": "Defence",
                "dateOfBirth": "1991-04-14",
                "nationality": "Spain",
                "shirtNumber": 2,
                "marketValue": 2000000,
                "contract": {
                    "start": "2020-08",
                    "until": "2024-06"
                }
            },
            {
                "id": 58580,
                "firstName": "",
                "lastName": "Édgar González",
                "name": "Édgar González",
                "position": "Defence",
                "dateOfBirth": "1997-04-01",
                "nationality": "Spain",
                "shirtNumber": 3,
                "marketValue": 5000000,
                "contract": {
                    "start": "2021-07",
                    "until": "2025-06"
                }
            },
            {
                "id": 144708,
                "firstName": "",
                "lastName": "Marc Baró",
                "name": "Marc Baró Ortiz",
                "position": "Defence",
                "dateOfBirth": "1999-08-23",
                "nationality": "Spain",
                "shirtNumber": 36,
                "marketValue": null,
                "contract": {
                    "start": "2022-01",
                    "until": "2022-06"
                }
            },
            {
                "id": 150595,
                "firstName": "",
                "lastName": "Fran Delgado",
                "name": "Fran Delgado",
                "position": "Defence",
                "dateOfBirth": "2001-07-11",
                "nationality": "Spain",
                "shirtNumber": 32,
                "marketValue": null,
                "contract": {
                    "start": "2020-09",
                    "until": "2024-06"
                }
            },
            {
                "id": 161288,
                "firstName": "",
                "lastName": "Kike Hermoso",
                "name": "Kike Hermoso",
                "position": "Defence",
                "dateOfBirth": "1999-08-10",
                "nationality": "Spain",
                "shirtNumber": 37,
                "marketValue": null,
                "contract": {
                    "start": "2021-07",
                    "until": "2022-06"
                }
            },
            {
                "id": 18,
                "firstName": "",
                "lastName": "Joaquín",
                "name": "Joaquín",
                "position": "Midfield",
                "dateOfBirth": "1981-07-21",
                "nationality": "Spain",
                "shirtNumber": 17,
                "marketValue": 1500000,
                "contract": {
                    "start": "2016-07",
                    "until": "2022-06"
                }
            },
            {
                "id": 25,
                "firstName": "",
                "lastName": "Canales",
                "name": "Canales",
                "position": "Midfield",
                "dateOfBirth": "1991-02-16",
                "nationality": "Spain",
                "shirtNumber": 10,
                "marketValue": 20000000,
                "contract": {
                    "start": "2019-07",
                    "until": "2026-06"
                }
            },
            {
                "id": 3250,
                "firstName": "",
                "lastName": "William Carvalho",
                "name": "William Carvalho",
                "position": "Midfield",
                "dateOfBirth": "1992-04-07",
                "nationality": "Portugal",
                "shirtNumber": 14,
                "marketValue": 14000000,
                "contract": {
                    "start": "2018-07",
                    "until": "2023-06"
                }
            },
            {
                "id": 8464,
                "firstName": "Nabil",
                "lastName": "Fekir",
                "name": "Nabil Fekir",
                "position": "Midfield",
                "dateOfBirth": "1993-07-18",
                "nationality": "France",
                "shirtNumber": 8,
                "marketValue": 30000000,
                "contract": {
                    "start": "2019-07",
                    "until": "2026-06"
                }
            },
            {
                "id": 33040,
                "firstName": "",
                "lastName": "Víctor Camarasa",
                "name": "Víctor Camarasa",
                "position": "Midfield",
                "dateOfBirth": "1994-05-28",
                "nationality": "Spain",
                "shirtNumber": 22,
                "marketValue": 1200000,
                "contract": {
                    "start": "2021-07",
                    "until": "2022-06"
                }
            },
            {
                "id": 39104,
                "firstName": "Guido",
                "lastName": "Rodríguez",
                "name": "Guido Rodríguez",
                "position": "Midfield",
                "dateOfBirth": "1994-04-12",
                "nationality": "Argentina",
                "shirtNumber": 21,
                "marketValue": 25000000,
                "contract": {
                    "start": "2020-01",
                    "until": "2024-06"
                }
            },
            {
                "id": 81737,
                "firstName": "Paul",
                "lastName": "Akouokou",
                "name": "Paul Akouokou",
                "position": "Midfield",
                "dateOfBirth": "1997-12-20",
                "nationality": "Ivory Coast",
                "shirtNumber": 4,
                "marketValue": 2000000,
                "contract": {
                    "start": "2020-09",
                    "until": "2024-06"
                }
            },
            {
                "id": 180064,
                "firstName": "",
                "lastName": "Marchena",
                "name": "Marchena",
                "position": "Midfield",
                "dateOfBirth": "2002-07-27",
                "nationality": "Spain",
                "shirtNumber": 38,
                "marketValue": null,
                "contract": {
                    "start": "2022-01",
                    "until": "2022-06"
                }
            },
            {
                "id": 2,
                "firstName": "",
                "lastName": "Juanmi",
                "name": "Juanmi",
                "position": "Offence",
                "dateOfBirth": "1993-05-20",
                "nationality": "Spain",
                "shirtNumber": 7,
                "marketValue": 12000000,
                "contract": {
                    "start": "2019-07",
                    "until": "2024-06"
                }
            },
            {
                "id": 8,
                "firstName": "",
                "lastName": "Willian José",
                "name": "Willian José",
                "position": "Offence",
                "dateOfBirth": "1991-11-23",
                "nationality": "Brazil",
                "shirtNumber": 12,
                "marketValue": 18000000,
                "contract": {
                    "start": "2021-08",
                    "until": "2022-06"
                }
            },
            {
                "id": 17,
                "firstName": "Christian",
                "lastName": null,
                "name": "Tello",
                "position": "Offence",
                "dateOfBirth": "1991-08-11",
                "nationality": "Spain",
                "shirtNumber": 11,
                "marketValue": 6000000,
                "contract": {
                    "start": "2017-07",
                    "until": "2022-06"
                }
            },
            {
                "id": 32056,
                "firstName": "",
                "lastName": "Borja Iglesias",
                "name": "Borja Iglesias",
                "position": "Offence",
                "dateOfBirth": "1993-01-17",
                "nationality": "Spain",
                "shirtNumber": 9,
                "marketValue": 10000000,
                "contract": {
                    "start": "2019-08",
                    "until": "2026-06"
                }
            },
            {
                "id": 33045,
                "firstName": "",
                "lastName": "Aitor Ruibal",
                "name": "Aitor Ruibal",
                "position": "Offence",
                "dateOfBirth": "1996-03-22",
                "nationality": "Spain",
                "shirtNumber": 24,
                "marketValue": 3000000,
                "contract": {
                    "start": "2020-07",
                    "until": "2025-06"
                }
            },
            {
                "id": 39115,
                "firstName": "Diego",
                "lastName": "Lainez",
                "name": "Diego Lainez",
                "position": "Offence",
                "dateOfBirth": "2000-06-09",
                "nationality": "Mexico",
                "shirtNumber": 20,
                "marketValue": 5000000,
                "contract": {
                    "start": "2019-07",
                    "until": "2024-06"
                }
            },
            {
                "id": 130324,
                "firstName": "",
                "lastName": "Raúl",
                "name": "Raúl",
                "position": "Offence",
                "dateOfBirth": "2000-11-03",
                "nationality": "Spain",
                "shirtNumber": 35,
                "marketValue": null,
                "contract": {
                    "start": "2019-09",
                    "until": "2022-06"
                }
            },
            {
                "id": 142393,
                "firstName": "",
                "lastName": "Rodri",
                "name": "Salomón Rodríguez",
                "position": "Offence",
                "dateOfBirth": "2000-02-16",
                "nationality": "Spain",
                "shirtNumber": 28,
                "marketValue": null,
                "contract": {
                    "start": "2020-06",
                    "until": "2026-06"
                }
            },
            {
                "id": 180037,
                "firstName": "",
                "lastName": "Cristian Tello",
                "name": "Cristian Tello",
                "position": "Offence",
                "dateOfBirth": "1991-08-11",
                "nationality": "Spain",
                "shirtNumber": 11,
                "marketValue": null,
                "contract": {
                    "start": "2018-07",
                    "until": "2022-06"
                }
            }
        ],
        "staff": [
            {
                "id": 63306,
                "firstName": "",
                "lastName": "Fernando",
                "name": "Fernando Fernández",
                "dateOfBirth": "1979-06-02",
                "nationality": "Spain",
                "contract": {
                    "start": "2020-08",
                    "until": "2023-06"
                }
            },
            {
                "id": 180098,
                "firstName": "",
                "lastName": "Doblas",
                "name": "Doblas",
                "dateOfBirth": "1980-08-05",
                "nationality": "Spain",
                "contract": {
                    "start": "2020-09",
                    "until": "2023-06"
                }
            },
            {
                "id": 180135,
                "firstName": "Rubén",
                "lastName": "Cousillas",
                "name": "Rubén Cousillas",
                "dateOfBirth": "1957-05-09",
                "nationality": "Argentina",
                "contract": {
                    "start": "2020-08",
                    "until": "2023-06"
                }
            }
        ],
        "lastUpdated": "2022-05-03T08:22:26Z"
    }
""".trimIndent()
    val team = remember { Gson().fromJson(jsonString, Team::class.java) }
    val context = LocalContext.current


    Log.d("TeamData", "Team Data: $team")
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Team Details", style = MaterialTheme.typography.titleLarge)

        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            // Team Logo and Name
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(team.crest)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                error = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "League Logo",
                contentScale = ContentScale.Inside,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = team.name, style = MaterialTheme.typography.titleMedium)
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

        Text(text = "Squad", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        // Table Header
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Player", style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f))
            Text(text = "Market Value", style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
        }


        LazyColumn {
            items(team.squad) { player ->
                SquadItem(player = player)
            }
        }
    }

}


@Composable
fun SquadItem(player: Player) {
    val formattedMarketValue = player.marketValue?.let {
        NumberFormat.getNumberInstance(Locale("fi","FI")).format(it.toLong())
    } ?: "N/a"
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${player.shirtNumber} - ${player.name}",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(2f) // Gives more space to the name
        )
        Text(
            text = player.position,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f) // Gives position less space than the name
        )
        Text(
            text = formattedMarketValue,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f) // Pushes market value to the end
        )
    }
}


