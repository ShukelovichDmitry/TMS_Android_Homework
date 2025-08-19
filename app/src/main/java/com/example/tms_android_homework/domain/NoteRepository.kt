package com.example.tms_android_homework.domain

import com.example.tms_android_homework.data.Note
import com.example.tms_android_homework.domain.models.NoteDetailModel

interface NoteRepository {
    suspend fun addNote(newNote: NoteDetailModel): Note?
    suspend fun getNotes(): List<Note>?
    suspend fun editNote(id: String, note: NoteDetailModel): Note?
    suspend fun deleteNote(id: String): Boolean
}