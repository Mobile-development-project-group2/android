package com.example.advancedandroidcourse.repository

import com.example.advancedandroidcourse.di.TeamRetrofit
import com.example.advancedandroidcourse.network.TeamApiService
import com.example.advancedandroidcourse.model.league.TeamDetails
import com.example.advancedandroidcourse.model.teamDetails.TeamDetailsResponse
import javax.inject.Inject

class TeamRepository @Inject constructor(
    @TeamRetrofit private val teamApiService: TeamApiService
) {

    suspend fun getTeamDetails(teamId: Int): TeamDetailsResponse {
        return teamApiService.getTeamDetails(teamId)
    }
}
