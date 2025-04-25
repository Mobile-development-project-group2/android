package com.example.advancedandroidcourse.di


import com.example.advancedandroidcourse.BuildConfig
import com.example.advancedandroidcourse.network.LeagueApiService
import com.example.advancedandroidcourse.network.TeamApiService
import com.example.advancedandroidcourse.repository.LeagueRepository
import com.example.advancedandroidcourse.repository.MatchRepository
import com.example.advancedandroidcourse.repository.TeamRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.example.advancedandroidcourse.network.MatchApiService

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    // TODO: Move every Retrofit instance to a separate module
    val LEAGUES_ENDPOINT = BuildConfig.LEAGUE_BASE_URL;
    val TEAMS_ENDPOINT = BuildConfig.TEAM_BASE_URL;
    val MATCH_ENDPOINT = BuildConfig.MATCH_BASE_URL;

    // Provides Retrofit instance for Leagues
    @Provides
    @Singleton
    @LeagueRetrofit
    fun provideLeagueRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LEAGUES_ENDPOINT)  // Leagues base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provides Retrofit instance for Teams
    @Provides
    @Singleton
    @TeamRetrofit
    fun provideTeamRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TEAMS_ENDPOINT)  // Teams base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provide LeagueApiService (for leagues)
    @Provides
    @Singleton
    fun provideLeagueApiService(@LeagueRetrofit retrofit: Retrofit): LeagueApiService {
        return retrofit.create(LeagueApiService::class.java)
    }

    // Provide TeamApiService (for teams)
    @Provides
    @Singleton
    fun provideTeamApiService(@TeamRetrofit retrofit: Retrofit): TeamApiService {
        return retrofit.create(TeamApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLeagueRepository(leagueApiService: LeagueApiService): LeagueRepository {
        return LeagueRepository(leagueApiService)
    }

    @Provides
    @Singleton
    fun provideTeamRepository(teamApiService: TeamApiService): TeamRepository {
        return TeamRepository(teamApiService)
    }

    @Provides
    @Singleton
    @MatchRetrofit
    fun provideMatchRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MATCH_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMatchApiService(@MatchRetrofit retrofit: Retrofit): MatchApiService {
        return retrofit.create(MatchApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMatchRepository(matchApiService: com.example.advancedandroidcourse.network.MatchApiService): MatchRepository {
        return MatchRepository(matchApiService)
    }


}

