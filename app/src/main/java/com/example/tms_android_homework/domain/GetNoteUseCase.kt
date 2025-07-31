package com.example.tms_android_homework.domain

import com.example.tms_android_homework.data.NoteEntity
import com.example.tms_android_homework.data.NoteRepository

class GetNoteUseCase(private val noteRepository: NoteRepository) {
    operator fun invoke(position: Int): NoteEntity {
        return noteRepository.getNote(position)
    }
}