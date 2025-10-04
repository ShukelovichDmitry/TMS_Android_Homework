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

    testImplementation("io.mockk:mockk:1.14.6")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")

    testImplementation("androidx.arch.core:core-testing:2.1.0")

    testImplementation("com.jraska.livedata:testing-ktx:1.3.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")

    //Coroutines test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")

    //Flow test
    testImplementation("app.cash.turbine:turbine:1.1.0")

    //Mockito
    testImplementation("org.mockito:mockito-core:5.11.0")

    //Mockito utils
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")

    //assert utils
    testImplementation(kotlin("test"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.leakcanary.android)

    //rxJava
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.adapter.rxjava3)
    implementation(libs.androidx.room.rxjava3)
    implementation(libs.kotlinx.coroutines.rx3)

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
    // Fragment KTX (for by viewModels())
    implementation(libs.fragment.ktx)

    //dataStore (sharedPrefs doesn't use deps)
    implementation(libs.androidx.datastore.preferences)

    // room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    //activity animation
    implementation(libs.animatoo)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}