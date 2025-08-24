package com.example.tms_android_homework.nbrb.domain

interface NbrbRepository {
    suspend fun getRates(): String?
}