package com.example.tms_android_homework.domain

import com.example.tms_android_homework.data.NoteEntity
import com.example.tms_android_homework.data.NoteRepository

class GetNotesUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(): List<NoteTitleModel> {
        return noteRepository.getNotes()
    }
}