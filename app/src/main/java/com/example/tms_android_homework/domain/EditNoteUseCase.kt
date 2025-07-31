package com.example.tms_android_homework.domain

import com.example.tms_android_homework.data.NoteEntity
import com.example.tms_android_homework.data.NoteRepository

class EditNoteUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(position: Int, title: String, text: String) {
        val note = NoteEntity(title, text)
        noteRepository.removeNote(position)
        noteRepository.editNote(position, note)
    }
}