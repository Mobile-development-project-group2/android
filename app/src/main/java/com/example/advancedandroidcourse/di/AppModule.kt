package com.example.advancedandroidcourse.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import com.google.firebase.FirebaseApp

class Myapplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase or any other SDK
        FirebaseApp.initializeApp(this)
    }
}

//Dependency Injection (Hilt)


@HiltAndroidApp
class MyApplication: Application(){

}