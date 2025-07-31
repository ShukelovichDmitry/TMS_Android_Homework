package com.example.tms_android_homework.data

import com.example.tms_android_homework.domain.NoteTitleModel

object NoteRepositoryImpl: NoteRepository {
    private val noteDataSource: NoteDataSource = NoteDataSource()

    override fun addNote(note: NoteEntity) {
        noteDataSource.saveNote(0, note)
    }

    override fun editNote(position: Int, note: NoteEntity) {
        noteDataSource.saveNote(position, note)
    }

    override fun getNote(position: Int): NoteEntity {
        return noteDataSource.getNote(position)
    }

    override fun getNotes(): List<NoteTitleModel> {
        return noteDataSource.getNotes()
    }

    override fun removeNote(position: Int) {
        noteDataSource.removeNote(position)
    }

    override fun removeAllNotes() {
        noteDataSource.removeAllMotes()
    }

}