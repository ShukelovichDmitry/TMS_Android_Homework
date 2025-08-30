package com.example.tms_android_homework.onboarding.domain

interface OnboardingRepository {
    suspend fun checkUserSawOnboarding(): Boolean
    suspend fun setUserSawOnboarding()
    suspend fun setUserSawOnboarding(wasSeen: Boolean)
}