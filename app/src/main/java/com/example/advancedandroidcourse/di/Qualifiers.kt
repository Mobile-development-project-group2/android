package com.example.advancedandroidcourse.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LeagueRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TeamRetrofit


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MatchRetrofit