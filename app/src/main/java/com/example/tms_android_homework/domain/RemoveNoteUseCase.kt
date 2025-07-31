package com.example.tms_android_homework.domain

import com.example.tms_android_homework.data.NoteRepository

class RemoveNoteUseCase (private val noteRepository: NoteRepository) {
    operator fun invoke(position: Int) {
        return noteRepository.removeNote(position)
    }
}