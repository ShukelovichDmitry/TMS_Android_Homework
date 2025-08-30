package com.example.tms_android_homework.onboarding.data

import com.example.tms_android_homework.onboarding.domain.OnboardingRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) : OnboardingRepository {

    override suspend fun checkUserSawOnboarding(): Boolean {
        return dataStoreManager.wasOnboardingSeen().first()
    }

    override suspend fun setUserSawOnboarding() {
        dataStoreManager.setUserSawOnboarding()
    }

    override suspend fun setUserSawOnboarding(wasSeen: Boolean) {
        dataStoreManager.setUserSawOnboarding(wasSeen)
    }
}