package com.example.tms_android_homework.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tms_android_homework.R
import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import com.example.tms_android_homework.note.domain.usecase.AddNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.DeleteNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.EditNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.GetNotesUseCase
import com.example.tms_android_homework.note.domain.usecase.SyncUseCase
import com.example.tms_android_homework.note.presentation.NoteViewModel
import com.jraska.livedata.test
import io.mockk.MockKAnnotations
import io.mockk.spyk
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


class NoteViewModelTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val addNote: AddNoteUseCase = Mockito.mock()
    private val editNote: EditNoteUseCase = Mockito.mock()
    private val deleteNote: DeleteNoteUseCase = Mockito.mock()
    private val getNotes: GetNotesUseCase = Mockito.mock()
    private val sync: SyncUseCase = Mockito.mock()

    private lateinit var sut: NoteViewModel

    private val favoriteNote = Note(
        id = "100",
        title = "test favorite title",
        description = "test favorite description",
        imageUrl = ""
    )

    private val favoriteNoteDetailModel = NoteDetailModel(
        title = "test favorite title",
        description = "test favorite description",
        imageUrl = ""
    )

    @Before
    fun setup() {
        val testScheduler = Schedulers.trampoline()
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }
        RxJavaPlugins.setSingleSchedulerHandler { testScheduler }
        RxAndroidPlugins.setMainThreadSchedulerHandler { testScheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }

        sut = NoteViewModel(addNote, editNote, deleteNote, getNotes, sync)
    }

    @After
    fun afterward() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun `noteViewModel calls getNotes`() {
        whenever(getNotes.invoke()).thenReturn(Flowable.just(listOf(favoriteNote)))

        sut.getNotes()

        sut.noteList.test().awaitValue().assertValue(listOf(favoriteNote))

        verify(getNotes, times(1)).invoke()
    }

    @Test
    fun `noteViewModel calls addNote`() {
        whenever(addNote.invoke(favoriteNote.title,favoriteNote.description, favoriteNote.imageUrl))
            .thenReturn(Single.just(favoriteNote))
        whenever(getNotes.invoke()).thenReturn(Flowable.just(listOf(favoriteNote)))

        sut.addNote(favoriteNote.title,favoriteNote.description, favoriteNote.imageUrl)

        sut.msg.test().awaitValue().assertValue(R.string.new_note_is_added_in_server)
        sut.noteList.test().awaitValue().assertValue(listOf(favoriteNote))

        verify(addNote, times(1)).invoke(favoriteNote.title,favoriteNote.description, favoriteNote.imageUrl)
        verify(getNotes, times(1)).invoke()
    }

    @Test
    fun `noteViewModel calls editNote`() {
        whenever(editNote.invoke(favoriteNote.id, favoriteNote.title,favoriteNote.description, favoriteNote.imageUrl))
            .thenReturn(Single.just(favoriteNote))
        whenever(getNotes.invoke()).thenReturn(Flowable.just(listOf(favoriteNote)))

        sut.editNote(favoriteNote.id, favoriteNote.title,favoriteNote.description, favoriteNote.imageUrl)

        sut.msg.test().awaitValue().assertValue(R.string.note_is_updated_in_server)
        sut.noteList.test().awaitValue().assertValue(listOf(favoriteNote))

        verify(editNote, times(1)).invoke(favoriteNote.id, favoriteNote.title,favoriteNote.description, favoriteNote.imageUrl)
        verify(getNotes, times(1)).invoke()
    }

    @Test
    fun `noteViewModel calls deleteNote`() {
        whenever(deleteNote.invoke(favoriteNote.id))
            .thenReturn(Single.just(true))
        whenever(getNotes.invoke()).thenReturn(Flowable.just(listOf()))

        sut.deleteNote(favoriteNote.id)

        sut.msg.test().awaitValue().assertValue(R.string.note_is_deleted_in_server)

        verify(deleteNote, times(1)).invoke(favoriteNote.id)
        verify(getNotes, times(1)).invoke()
    }

}







