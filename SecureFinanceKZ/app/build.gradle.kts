plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "kz.diploma.securefinance"
    compileSdk = 34

    defaultConfig {
        applicationId = "kz.diploma.securefinance"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // SQLCipher ABI-лері
        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore/release.jks")
            storePassword = System.getenv("KEYSTORE_PASS") ?: "securefinance2025"
            keyAlias = "securefinance"
            keyPassword = System.getenv("KEY_PASS") ?: "securefinance2025"
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
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
        buildConfig = true
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.fragment.ktx)

    // Room + SQLCipher
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.sqlcipher)
    implementation(libs.sqlite.ktx)

    // Security
    implementation(libs.security.crypto)
    implementation(libs.biometric)

    // Lifecycle
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.runtime)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Coroutines
    implementation(libs.coroutines.android)

    // Charts
    implementation(libs.mpandroidchart)

    // JSON
    implementation(libs.gson)

    // Tests
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.coroutines.test)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso.core)
}
