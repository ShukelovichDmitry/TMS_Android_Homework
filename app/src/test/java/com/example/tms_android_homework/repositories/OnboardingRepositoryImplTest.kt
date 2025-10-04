package com.example.tms_android_homework.repositories

import androidx.constraintlayout.helper.widget.Flow
import com.example.tms_android_homework.onboarding.data.DataStoreManager
import com.example.tms_android_homework.onboarding.data.OnboardingRepositoryImpl
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertTrue

class OnboardingRepositoryImplTest {
    private val dataStoreManager: DataStoreManager = Mockito.mock()
    private lateinit var sut: OnboardingRepositoryImpl

    @Before
    fun setup() {
        sut = OnboardingRepositoryImpl(dataStoreManager)
    }

    @Test
    fun `onboardingRepositoryImpl calls checkUserSawOnboarding`() = runTest {
        whenever(dataStoreManager.wasOnboardingSeen()).thenReturn(flowOf(true))
        assertTrue(sut.checkUserSawOnboarding())
        verify(dataStoreManager, times(1)).wasOnboardingSeen()
    }

    @Test
    fun `onboardingRepositoryImpl calls setUserSawOnboarding`() = runTest {
        sut.setUserSawOnboarding()
        verify(dataStoreManager, times(1)).setUserSawOnboarding()
    }
}







