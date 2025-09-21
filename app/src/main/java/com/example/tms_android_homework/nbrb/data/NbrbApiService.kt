package com.example.tms_android_homework.nbrb.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NbrbApiService {
    @GET("rates")
    fun fetchRates(@Query("periodicity") periodicity: Int): Single<String> //List<Rate>?
}