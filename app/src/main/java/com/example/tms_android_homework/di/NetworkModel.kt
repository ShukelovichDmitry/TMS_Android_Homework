package com.example.tms_android_homework.di

import com.example.tms_android_homework.note.data.MockApiService
import com.example.tms_android_homework.nbrb.data.NbrbApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModel {

    @Provides
    @Singleton
    fun provideLoggerIntercepter(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggerIntercepter: HttpLoggingInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggerIntercepter)
            .build()
        return client
    }

    @Provides
    @Singleton
    @Named("mockapi")
    fun provideMockRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder() //the baseUrl should end with /
            .baseUrl("https://689f0e323fed484cf878e4c7.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMockApiService(@Named("mockapi") retrofit: Retrofit): MockApiService {
        return retrofit.create(MockApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("nbrb")
    fun provideNbrbRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder() //the baseUrl should end with /
            .baseUrl("https://api.nbrb.by/exrates/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideNbrbApiService(@Named("nbrb") retrofit: Retrofit): NbrbApiService {
        return retrofit.create(NbrbApiService::class.java)
    }
}