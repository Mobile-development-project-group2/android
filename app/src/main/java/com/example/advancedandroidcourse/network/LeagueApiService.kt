package com.example.advancedandroidcourse.network


import com.example.advancedandroidcourse.viewmodel.LeagueDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface LeagueApiService{

    @GET("{leagueCode}")
    suspend fun getLeagueDetails(@Path("leagueCode") leagueCode: String): LeagueDetailsResponse


}