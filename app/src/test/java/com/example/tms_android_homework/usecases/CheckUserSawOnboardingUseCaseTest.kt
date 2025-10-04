package com.example.tms_android_homework.usecases

import com.example.tms_android_homework.onboarding.domain.OnboardingRepository
import com.example.tms_android_homework.onboarding.domain.usecase.CheckUserSawOnboardingUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertTrue

class CheckUserSawOnboardingUseCaseTest {
    private val onboardingRepositoryImpl: OnboardingRepository = Mockito.mock()
    private lateinit var sut: CheckUserSawOnboardingUseCase

    @Before
    fun setup() {
        sut = CheckUserSawOnboardingUseCase(onboardingRepositoryImpl)
    }

    @Test
    fun `checkUserSawOnboardingUseCaseTest calls checkUserSawOnboarding`() = runTest {
        whenever(onboardingRepositoryImpl.checkUserSawOnboarding()).thenReturn(true)
        assertTrue(sut.invoke())
        verify(onboardingRepositoryImpl, times(1)).checkUserSawOnboarding()
    }
}