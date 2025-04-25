package com.example.advancedandroidcourse.model.matches


data class MatchByDate(
    val homeTeam: String,
    val awayTeam: String,
    val date: String,
    val time: String
)

data class MatchesByDateResponse(
    val `La Liga`: List<MatchByDate>,
    val `Premier League`: List<MatchByDate>,
    val `Ligue 1`: List<MatchByDate>,
    val `Bundesliga`: List<MatchByDate>,
    val `Serie A`: List<MatchByDate>
)


data class ErrorResponse(
    val success: Boolean,
    val error: String
)


sealed class MatchesByDateUiState {
    object Loading : MatchesByDateUiState()
    data class Success(val matches: MatchesByDateResponse) : MatchesByDateUiState()
    data class Error(val message: String) : MatchesByDateUiState()
}






