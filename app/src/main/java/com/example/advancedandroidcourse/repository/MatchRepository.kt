package com.example.advancedandroidcourse.repository

import com.example.advancedandroidcourse.di.MatchRetrofit
import com.example.advancedandroidcourse.model.matches.MatchesByDateResponse
import com.example.advancedandroidcourse.network.MatchApiService
import javax.inject.Inject

class MatchRepository @Inject constructor(
    @MatchRetrofit private val matchApiService: MatchApiService
) {
    suspend fun getMatchesByDate(date: String): MatchesByDateResponse {
        return matchApiService.getMatchesByDate(date)
    }
}
