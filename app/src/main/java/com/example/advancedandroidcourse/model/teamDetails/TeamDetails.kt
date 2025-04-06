package com.example.advancedandroidcourse.model.teamDetails


import com.google.gson.annotations.SerializedName

data class TeamDetailsResponse(
    val success: Boolean,
    val data: TeamDetails
)

data class TeamDetails(
    val area: Area,
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
    val runningCompetitions: List<RunningCompetition>,
    val coach: Coach,
    val squad: List<Player>,
    val staff: List<Any>,
    val lastUpdated: String
)

data class Area(
    val id: Int,
    val name: String,
    val code: String,
    val flag: String
)

data class RunningCompetition(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String
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
    val name: String,
    val position: String,
    val dateOfBirth: String,
    val nationality: String
)
