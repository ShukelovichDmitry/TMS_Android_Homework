package com.example.tms_android_homework.usecases

import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import com.example.tms_android_homework.note.domain.usecase.EditNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.GetNotesUseCase
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetNotesUseCaseTest {
    private val noteRepository: NoteRepository = Mockito.mock()
    private lateinit var sut: GetNotesUseCase

    @Before
    fun setup() {
        sut = GetNotesUseCase(noteRepository)
    }

    @Test
    fun `getNotesUseCase calls getNotes`() = runTest {
        whenever(noteRepository.getNotes()).thenReturn(Flowable.just(emptyList()))

        sut.invoke().test().await().assertValue(emptyList())
        verify(noteRepository, times(1)).getNotes()
    }
}