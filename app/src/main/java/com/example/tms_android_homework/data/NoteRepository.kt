package com.example.tms_android_homework.data

import com.example.tms_android_homework.domain.NoteTitleModel

interface NoteRepository {
    fun addNote(note: NoteEntity)
    fun editNote(position: Int, note: NoteEntity)
    fun getNote(position: Int): NoteEntity
    fun getNotes(): List<NoteTitleModel>
    fun removeNote(position: Int)
    fun removeAllNotes()
}