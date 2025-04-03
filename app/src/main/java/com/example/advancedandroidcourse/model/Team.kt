package com.example.advancedandroidcourse.model

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

