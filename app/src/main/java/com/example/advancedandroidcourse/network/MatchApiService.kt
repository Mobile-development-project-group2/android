package com.example.advancedandroidcourse.network


import com.example.advancedandroidcourse.model.matches.MatchesByDateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchApiService {

    @GET("matches")
    suspend fun getMatchesByDate(@Query("date") date: String): MatchesByDateResponse
}
