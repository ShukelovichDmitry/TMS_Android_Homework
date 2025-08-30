package com.example.tms_android_homework.onboarding.domain.usecase

import com.example.tms_android_homework.onboarding.domain.OnboardingRepository
import javax.inject.Inject

class CheckUserSawOnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {

    suspend operator fun invoke() = onboardingRepository.checkUserSawOnboarding()
}