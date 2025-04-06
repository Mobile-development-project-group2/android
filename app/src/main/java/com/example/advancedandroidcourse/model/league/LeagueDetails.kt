data class LeagueDetails(
    val filters: Filters,
    val area: Area,
    val competition: Competition,
    val season: Season,
    val standings: List<Standings> // Note: Changed to `List<Standings>`
)

data class Standings(
    val stage: String,
    val type: String,
    val group: String?, // `group` can be null
    val table: List<Standing> // The `table` field contains the actual list of standings
)

data class Standing(
    val position: Int,
    val team: Team,
    val playedGames: Int,
    val form: String?,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val points: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int
)

data class Filters(
    val season: String
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
    val emblem: String
)

data class Season(
    val id: Int,
    val startDate: String,
    val endDate: String,
)

data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    val tla: String,
    val crest: String
)
