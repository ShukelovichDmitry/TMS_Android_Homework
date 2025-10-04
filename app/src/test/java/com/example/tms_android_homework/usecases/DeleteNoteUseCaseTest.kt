package com.example.tms_android_homework.usecases

import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import com.example.tms_android_homework.note.domain.usecase.AddNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.DeleteNoteUseCase
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DeleteNoteUseCaseTest {
    private val noteRepository: NoteRepository = Mockito.mock()
    private lateinit var sut: DeleteNoteUseCase

    @Before
    fun setup() {
        sut = DeleteNoteUseCase(noteRepository)
    }

    @Test
    fun `deleteNoteUseCase calls deleteNote`() = runTest {
        val id = "1"
        whenever(noteRepository.deleteNote(id)).thenReturn(Single.just(true))
        sut.invoke(id).test().await().assertValue(true)
        verify(noteRepository, times(1)).deleteNote(id)
    }
}