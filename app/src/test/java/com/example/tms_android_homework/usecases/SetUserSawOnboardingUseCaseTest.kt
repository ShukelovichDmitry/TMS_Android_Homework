package com.example.tms_android_homework.usecases

import com.example.tms_android_homework.onboarding.domain.OnboardingRepository
import com.example.tms_android_homework.onboarding.domain.usecase.SetUserSawOnboardingUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class SetUserSawOnboardingUseCaseTest {
    private val onboardingRepositoryImpl: OnboardingRepository = Mockito.mock()
    private lateinit var sut: SetUserSawOnboardingUseCase

    @Before
    fun setup() {
        sut = SetUserSawOnboardingUseCase(onboardingRepositoryImpl)
    }

    @Test
    fun `checkUserSawOnboardingUseCaseTest calls checkUserSawOnboarding`() = runTest {
        sut.invoke()
        verify(onboardingRepositoryImpl, times(1)).setUserSawOnboarding()
    }
}