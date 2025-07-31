package com.example.tms_android_homework.data

import com.example.tms_android_homework.domain.NoteTitleModel

class NoteDataSource {
    private val noteList = mutableListOf<NoteEntity>()

    fun saveNote(position: Int, note: NoteEntity) {
        noteList.add(position, note)
    }

    fun getNote(position: Int): NoteEntity {
        return noteList.get(position)
    }

    fun getNotes(): List<NoteTitleModel> {
        return noteList.map { NoteTitleModel(it.title) }
    }

    fun removeNote(position: Int) {
        noteList.removeAt(position)
    }

    fun removeAllMotes() {
        noteList.clear()
    }
}