plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.newsprojectpractice"
    compileSdk = 34 // Updated to the latest SDK version

    defaultConfig {
        applicationId = "com.example.newsprojectpractice"
        minSdk = 28 // This is fine, unless you want to raise the minSdk.
        targetSdk = 34 // Updated to the latest SDK version
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core dependencies
    implementation("androidx.core:core-ktx:1.12.0") // Latest stable version
    implementation("androidx.appcompat:appcompat:1.6.1") // Latest stable version
    implementation("com.google.android.material:material:1.10.0") // Latest stable version
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // Latest stable version

    // Test dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Architecture Components (ViewModel, LiveData, Lifecycle)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0") // Updated to the latest
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") // Updated to the latest
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0") // Updated to the latest

    // Room Database
    implementation("androidx.room:room-runtime:2.6.0") // Latest Room version
    ksp("androidx.room:room-compiler:2.6.0") // KSP for Room compiler
    implementation("androidx.room:room-ktx:2.6.0") // Room extensions for Kotlin

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2") // Latest stable version
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2") // Latest stable version

    // Retrofit for network calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Latest stable version
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson converter for Retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0") // Latest stable version of OkHttp

    // Navigation Components (SafeArgs for argument passing between fragments)
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5") // Updated to the latest version
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5") // Updated to the latest version

    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.15.0") // Updated to latest stable version
    ksp("com.github.bumptech.glide:compiler:4.15.0") // Glide KSP compiler

    // Gson for JSON parsing
    implementation("com.google.code.gson:gson:2.10.1") // Latest stable version
}
