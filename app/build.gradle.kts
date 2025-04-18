plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt) // Apply Hilt plugin from version catalog
    id("com.google.gms.google-services")  // Apply Google Services plugin
    alias(libs.plugins.kapt) // Enable KAPT for Hilt
    id("kotlin-kapt") // Ensure Kotlin KAPT is included
}

android {
    namespace = "com.example.advancedandroidcourse"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.advancedandroidcourse"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "META-INF/versions/9/OSGI-INF/MANIFEST.MF"
        }
    }
}

dependencies {
    // AndroidX dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.navigation.runtime.ktx)

    // Firebase Authentication and Firestore
    implementation(platform(libs.firebase.bom)) // Firebase BOM (manage versions)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth) // Firebase auth SDK

    // Google SignIn and other Firebase dependencies
    implementation(libs.googleid)  // Google Sign-In dependency
    implementation(libs.play.services.auth)  // Google Play services auth

    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.identity.doctypes.jvm)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)

    // Navigation and Lifecycle components
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)

    // Image loading with Coil
    implementation(libs.coil3.coil.compose)
    implementation(libs.coil3.coil.network.okhttp)

    // Retrofit, Gson and other dependencies
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //socket
    implementation(libs.socket.io.client)

    implementation(libs.firebase.messaging)


    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}
