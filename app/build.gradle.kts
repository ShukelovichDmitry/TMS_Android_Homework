plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.tms_android_homework"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.tms_android_homework"
        minSdk = 33
        targetSdk = 36
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
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.leakcanary.android)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //gson
    implementation(libs.gson)
    implementation(libs.converter.scalars)

    //retrofit
    implementation(libs.retrofit)
    //gson to convert(serialize) api response to our kotlin data models
    implementation(libs.converter.gson)
    //api logger
    implementation(libs.logging.interceptor)

    //glide - for images
    implementation(libs.glide)
    kapt(libs.compiler)

    // viewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Lifecycle Scope
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Activity KTX (for by viewModels())
    implementation(libs.androidx.activity.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}