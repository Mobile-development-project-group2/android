package com.example.advancedandroidcourse.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import com.google.firebase.FirebaseApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase or any other SDK
        FirebaseApp.initializeApp(this)
    }
}

