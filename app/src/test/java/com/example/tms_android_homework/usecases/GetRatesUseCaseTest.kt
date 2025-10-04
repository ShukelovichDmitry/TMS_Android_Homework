package com.example.tms_android_homework.usecases

import com.example.tms_android_homework.nbrb.domain.NbrbRepository
import com.example.tms_android_homework.nbrb.domain.usecase.GetRatesUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class GetRatesUseCaseTest {
    private val nbrbRepository: NbrbRepository = Mockito.mock()
    private lateinit var sut: GetRatesUseCase

    @Before
    fun setup() {
        sut = GetRatesUseCase(nbrbRepository)
    }

    @Test
    fun `getRatesUseCase calls getRates`() = runTest {
        sut.invoke()
        verify(nbrbRepository, times(1)).getRates()
    }
}