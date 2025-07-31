package com.example.tms_android_homework.domain

import com.example.tms_android_homework.data.NoteRepository

class RemoveAllNotesUseCase (private val noteRepository: NoteRepository) {
    operator fun invoke() {
        return noteRepository.removeAllNotes()
    }
}