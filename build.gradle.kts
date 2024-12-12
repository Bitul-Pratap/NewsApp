// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()      // Required for Android libraries
        mavenCentral() // For Kotlin and some other libraries
    }
    dependencies {
        // Safe Args plugin version for Navigation
        val navVersion = "2.7.5"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}

plugins {
    // Android application plugin
    id("com.android.application") version "8.1.1" apply false

    // Kotlin plugin
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    // Kotlin Symbol Processing plugin (KSP)
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}
