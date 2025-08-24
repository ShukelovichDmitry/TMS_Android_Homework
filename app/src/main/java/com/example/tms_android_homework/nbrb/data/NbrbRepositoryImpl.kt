package com.example.tms_android_homework.nbrb.data

import com.example.tms_android_homework.nbrb.domain.NbrbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NbrbRepositoryImpl @Inject constructor(private val apiService: NbrbApiService):
    NbrbRepository {
    override suspend fun getRates(): String? =
        withContext(Dispatchers.IO) {
            try {
                apiService.fetchRates(0)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
}