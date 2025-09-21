package com.example.tms_android_homework.nbrb.domain

import io.reactivex.rxjava3.core.Single

interface NbrbRepository {
    fun getRates(): Single<String>
}