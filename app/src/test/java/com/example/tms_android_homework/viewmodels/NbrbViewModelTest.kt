package com.example.tms_android_homework.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tms_android_homework.nbrb.domain.usecase.GetRatesUseCase
import com.example.tms_android_homework.nbrb.presentation.NbrbViewModel
import com.example.tms_android_homework.note.presentation.NoteViewModel
import com.jraska.livedata.test
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NbrbViewModelTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getRates: GetRatesUseCase = Mockito.mock()

    private lateinit var sut: NbrbViewModel

    @Before
    fun setup() {
        val testScheduler = Schedulers.trampoline()
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }
        RxJavaPlugins.setSingleSchedulerHandler { testScheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { testScheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }

        sut = NbrbViewModel(getRates)
    }

    @After
    fun afterward() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun `nbrbViewModel calls getRates`() {
        whenever(getRates.invoke()).thenReturn(Single.just(""))

        sut.getRates()

        sut.rateJSON.test().awaitValue().assertValue("")

        verify(getRates, times(1)).invoke()
    }
}