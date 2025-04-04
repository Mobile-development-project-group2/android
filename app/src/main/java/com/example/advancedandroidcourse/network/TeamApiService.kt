package com.example.advancedandroidcourse.network


import com.example.advancedandroidcourse.model.league.TeamDetails
import com.example.advancedandroidcourse.model.teamDetails.TeamDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamApiService {

    @GET("{teamId}")
    suspend fun getTeamDetails(@Path("teamId") teamId: Int): TeamDetailsResponse
}
