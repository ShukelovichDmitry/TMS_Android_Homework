package com.example.tms_android_homework.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tms_android_homework.data.NoteEntity
import com.example.tms_android_homework.data.NoteRepositoryImpl
import com.example.tms_android_homework.domain.AddNoteUseCase
import com.example.tms_android_homework.domain.EditNoteUseCase
import com.example.tms_android_homework.domain.GetNoteUseCase
import com.example.tms_android_homework.domain.GetNotesUseCase
import com.example.tms_android_homework.domain.NoteTitleModel
import com.example.tms_android_homework.domain.RemoveAllNotesUseCase
import com.example.tms_android_homework.domain.RemoveNoteUseCase

object NoteViewModel: ViewModel() {
    private val _notes = MutableLiveData<List<NoteTitleModel>>(emptyList())
    private val addNote = AddNoteUseCase(NoteRepositoryImpl)
    private val editNote = EditNoteUseCase(NoteRepositoryImpl)
    private val removeNote = RemoveNoteUseCase(NoteRepositoryImpl)
    private val removeAllNotes = RemoveAllNotesUseCase(NoteRepositoryImpl)
    private val getNotes = GetNotesUseCase(NoteRepositoryImpl)
    private val getNote = GetNoteUseCase(NoteRepositoryImpl)

    val notes: LiveData<List<NoteTitleModel>>
        get() = _notes

    fun addNote(title: String, text: String) {
        addNote.invoke(title, text)
        _notes.value = getNotes.invoke()
    }

    fun getNote(position: Int): NoteEntity {
        return getNote.invoke(position)
    }

    fun editNote(position: Int, title: String, text: String) {
        editNote.invoke(position, title, text)
        _notes.value = getNotes.invoke()
    }

    fun removeNote(position: Int) {
        removeNote.invoke(position)
        _notes.value = getNotes.invoke()
    }

    fun removeAllNotes() {
        removeAllNotes.invoke()
        _notes.value = getNotes.invoke()
    }
}