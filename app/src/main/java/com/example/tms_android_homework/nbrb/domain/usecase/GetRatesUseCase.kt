package com.example.tms_android_homework.nbrb.domain.usecase

import com.example.tms_android_homework.nbrb.domain.NbrbRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(private val nbrbRepository: NbrbRepository) {
    fun invoke(): Single<String> {
        return nbrbRepository.getRates()
    }
}