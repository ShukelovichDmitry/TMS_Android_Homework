package com.example.tms_android_homework

import android.app.Application
import com.example.tms_android_homework.di.AppComponent
import com.example.tms_android_homework.di.AppModule
import com.example.tms_android_homework.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    fun provideAppComponent(): AppComponent {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        return appComponent
    }
}