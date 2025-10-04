package com.example.tms_android_homework.usecases

import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import com.example.tms_android_homework.note.domain.usecase.AddNoteUseCase
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AddNoteUseCaseTest {
    private val noteRepository: NoteRepository = Mockito.mock()
    private lateinit var sut: AddNoteUseCase

    @Before
    fun setup() {
        sut = AddNoteUseCase(noteRepository)
    }

    @Test
    fun `addNoteUseCase calls addNote`() = runTest {
        val title = "new title"
        val description = "new description"
        val imageUrl = ""
        val noteDetailModel = NoteDetailModel(title, description, imageUrl)
        val note = Note("1", title, description, imageUrl)

        whenever(noteRepository.addNote(noteDetailModel)).thenReturn(Single.just(note))
        sut.invoke(title, description, imageUrl).test().await().assertValue(note)
        verify(noteRepository, times(1)).addNote(noteDetailModel)
    }
}