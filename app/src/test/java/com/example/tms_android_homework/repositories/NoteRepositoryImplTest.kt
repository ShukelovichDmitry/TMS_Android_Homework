package com.example.tms_android_homework.repositories

import com.example.tms_android_homework.note.data.MockApiService
import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.data.NoteRepositoryImpl
import com.example.tms_android_homework.note.data.db.NoteDAO
import com.example.tms_android_homework.note.data.db.NoteEntity
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

class NoteRepositoryImplTest {
    private val apiService: MockApiService = Mockito.mock()
    private val noteDAO: NoteDAO = Mockito.mock()
    private lateinit var sut: NoteRepositoryImpl

    private val notFavoriteNote = Note(
        id = "100",
        title = "test not favorite title",
        description = "test not favorite description",
        imageUrl = ""
    )

    private val favoriteNote = Note(
        id = "101",
        title = "test favorite title",
        description = "test favorite description",
        imageUrl = ""
    )

    private val notFavoriteNoteEntity = NoteEntity(
        id = notFavoriteNote.id,
        title = notFavoriteNote.title,
        description = notFavoriteNote.description,
        imageUrl = notFavoriteNote.imageUrl,
        isNew = false,
        isUpdated = false,
        isDeleted = false
    )

    private val favoriteNoteEntity = NoteEntity(
        id = favoriteNote.id,
        title = favoriteNote.title,
        description = favoriteNote.description,
        imageUrl = favoriteNote.imageUrl,
        isNew = false,
        isUpdated = false,
        isDeleted = false
    )

    private val noteList = listOf(notFavoriteNote, favoriteNote)
    private val noteEntityList = listOf(notFavoriteNoteEntity, favoriteNoteEntity)

    @Before
    fun setup() {
        sut = NoteRepositoryImpl(apiService = apiService, noteDAO = noteDAO)
    }

    @Test
    fun `noteRepositoryImpl calls getNotes`() = runTest {
        whenever(noteDAO.getAllEntities()).thenReturn(Flowable.just(emptyList()))
        whenever(apiService.fetchNotes()).thenReturn(Single.just(noteList))

        sut.getNotes().test().await().assertValue(noteList)

        verify(apiService, times(1)).fetchNotes()
        verify(noteDAO, times(1)).insertAll(noteEntityList)
        verify(noteDAO, times(1)).getAllEntities()
    }

    @Test
    fun `noteRepositoryImpl calls addNote`() = runTest {
        val newNote = NoteDetailModel(
            title =  favoriteNote.title,
            description = favoriteNote.description,
            imageUrl = favoriteNote.imageUrl
        )
        whenever(apiService.createPost(newNote)).thenReturn(Single.just(favoriteNote))
        whenever(noteDAO.getNotesLastId()).thenReturn(100)

        sut.addNote(newNote).test().await().assertValue(favoriteNote)

        verify(apiService, times(1)).createPost(newNote)
    }

    @Test
    fun `noteRepositoryImpl calls editNote`() = runTest {
        val newNote = NoteDetailModel(
            title =  favoriteNote.title,
            description = favoriteNote.description,
            imageUrl = favoriteNote.imageUrl
        )
        whenever(apiService.updateNote(favoriteNote.id, newNote)).thenReturn(Single.just(favoriteNote))

        sut.editNote(favoriteNote.id, newNote).test().await().assertValue(favoriteNote)

        verify(apiService, times(1)).updateNote(favoriteNote.id, newNote)
    }

    @Test
    fun `noteRepositoryImpl calls deleteNote`() = runTest {
        whenever(apiService.deleteNote(favoriteNote.id)).thenReturn(Single.just(Response.success(Unit)))

        sut.deleteNote(favoriteNote.id).test().await().assertValue(true)

        verify(apiService, times(1)).deleteNote(favoriteNote.id)
    }

}