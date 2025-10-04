package com.example.tms_android_homework.viewmodels

import app.cash.turbine.test
import com.example.tms_android_homework.R
import com.example.tms_android_homework.note.presentation.NoteViewModel
import com.example.tms_android_homework.onboarding.domain.usecase.CheckUserSawOnboardingUseCase
import com.example.tms_android_homework.onboarding.domain.usecase.SetUserSawOnboardingUseCase
import com.example.tms_android_homework.onboarding.presentation.OnboardingViewModel
import com.jraska.livedata.test
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OnboardingViewModelTest {
    private val setUserSawOnboardingUseCase: SetUserSawOnboardingUseCase = Mockito.mock()
    private val checkUserSawOnboarding: CheckUserSawOnboardingUseCase = Mockito.mock()

    private lateinit var sut: OnboardingViewModel

    @Test
    fun `onboardingViewModelTest init`() = runTest{
        val sut = OnboardingViewModel(setUserSawOnboardingUseCase, checkUserSawOnboarding)

        whenever(checkUserSawOnboarding.invoke()).thenReturn(false)

        sut.isOnboardingChecked.test {
            val res = awaitItem()
            assertFalse(res)
        }

        verify(checkUserSawOnboarding, times(1)).invoke()
    }

    @Test
    fun `onboardingViewModelTest calls goToNotesList`() = runTest{
        val sut = OnboardingViewModel(setUserSawOnboardingUseCase, checkUserSawOnboarding)

        whenever(setUserSawOnboardingUseCase.invoke()).thenReturn(Unit)

        sut.goToNotesList()

        sut.isOnboardingChecked.test {
            val res = awaitItem()
            assertTrue(res)
        }

        verify(setUserSawOnboardingUseCase, times(1)).invoke()
    }
}