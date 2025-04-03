package com.example.advancedandroidcourse.di

import com.example.advancedandroidcourse.network.LeagueApiService
import com.example.advancedandroidcourse.repository.LeagueRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.110:3000/api/competitions/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLeagueApiService(retrofit: Retrofit): LeagueApiService {
        return retrofit.create(LeagueApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLeagueRepository(leagueApiService: LeagueApiService): LeagueRepository {
        return LeagueRepository(leagueApiService)
    }
}
