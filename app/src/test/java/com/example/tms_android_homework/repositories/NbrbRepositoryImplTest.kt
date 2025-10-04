package com.example.tms_android_homework.repositories

import com.example.tms_android_homework.nbrb.data.NbrbApiService
import com.example.tms_android_homework.nbrb.data.NbrbRepositoryImpl
import com.example.tms_android_homework.note.data.MockApiService
import com.example.tms_android_homework.note.data.NoteRepositoryImpl
import com.example.tms_android_homework.note.data.db.NoteDAO
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NbrbRepositoryImplTest {
    private val apiService: NbrbApiService = Mockito.mock()
    private lateinit var sut: NbrbRepositoryImpl

    @Before
    fun setup() {
        sut = NbrbRepositoryImpl(apiService)
    }

    @Test
    fun `noteRepositoryImpl calls getNotes`() = runTest {
        whenever(apiService.fetchRates(0)).thenReturn(Single.just(""))

        sut.getRates().test().await().assertValue("")

        verify(apiService, times(1)).fetchRates(0)
    }

}