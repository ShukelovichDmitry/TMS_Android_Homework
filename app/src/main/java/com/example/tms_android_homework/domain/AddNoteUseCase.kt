package com.example.tms_android_homework.domain

import com.example.tms_android_homework.data.NoteEntity
import com.example.tms_android_homework.data.NoteRepository

class AddNoteUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(title: String, text: String) {
        val note = NoteEntity(title, text)
        noteRepository.addNote(note)
    }
}