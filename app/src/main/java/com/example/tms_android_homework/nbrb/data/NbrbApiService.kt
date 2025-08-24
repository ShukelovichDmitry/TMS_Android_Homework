package com.example.tms_android_homework.nbrb.data

import retrofit2.http.GET
import retrofit2.http.Query

interface NbrbApiService {
    @GET("rates")
    suspend fun fetchRates(@Query("periodicity") periodicity: Int): String? //List<Rate>?
}