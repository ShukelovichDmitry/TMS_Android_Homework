package com.example.tms_android_homework.usecases

import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import com.example.tms_android_homework.note.domain.usecase.DeleteNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.EditNoteUseCase
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class EditNoteUseCaseTest {
    private val noteRepository: NoteRepository = Mockito.mock()
    private lateinit var sut: EditNoteUseCase

    @Before
    fun setup() {
        sut = EditNoteUseCase(noteRepository)
    }

    @Test
    fun `editNoteUseCase calls editNote`() = runTest {
        val id = "1"
        val title = "new title"
        val description = "new description"
        val imageUrl = ""
        val note = Note("1", title, description, imageUrl)
        val noteDetailModel = NoteDetailModel(title, description, imageUrl)

        whenever(noteRepository.editNote(id, noteDetailModel)).thenReturn(Single.just(note))
        sut.invoke(id, title, description, imageUrl).test().await().assertValue(note)
        verify(noteRepository, times(1)).editNote(id, noteDetailModel)
    }
}