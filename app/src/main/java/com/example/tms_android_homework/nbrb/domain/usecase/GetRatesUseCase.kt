package com.example.tms_android_homework.nbrb.domain.usecase

import com.example.tms_android_homework.nbrb.domain.NbrbRepository
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(private val nbrbRepository: NbrbRepository) {
    suspend fun invoke(): String? {
        return nbrbRepository.getRates()
    }
}