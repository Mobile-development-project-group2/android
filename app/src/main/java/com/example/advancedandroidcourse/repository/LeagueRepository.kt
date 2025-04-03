package com.example.advancedandroidcourse.repository

import com.example.advancedandroidcourse.network.LeagueApiService
import com.example.advancedandroidcourse.viewmodel.LeagueDetailsResponse
import javax.inject.Inject

class LeagueRepository @Inject constructor(
    private val leagueApiService: LeagueApiService
){

    suspend fun getLeagueDetails(leagueCode: String) : LeagueDetailsResponse {
        return leagueApiService.getLeagueDetails(leagueCode)
    }

    // Add other repository methods as needed
}