buildscript {
    repositories {
        google()  // Ensure google() is in repositories block
        mavenCentral()
    }
    dependencies {
        // Firebase and Hilt Gradle Plugin dependencies
        classpath(libs.google.services)  // Google services plugin version
        classpath(libs.hilt.android.gradle.plugin)  // Hilt plugin
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kapt) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false  // Apply google-services
}
