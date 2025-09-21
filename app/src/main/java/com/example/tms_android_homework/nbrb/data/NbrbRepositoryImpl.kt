package com.example.tms_android_homework.nbrb.data

import com.example.tms_android_homework.nbrb.domain.NbrbRepository
import javax.inject.Inject

class NbrbRepositoryImpl @Inject constructor(private val apiService: NbrbApiService):
    NbrbRepository {
    override fun getRates() = apiService.fetchRates(0)
}