package com.example.tms_android_homework.domain.usecase

import com.example.tms_android_homework.domain.NoteRepository

class DeleteNoteUseCase(private val itemRepository: NoteRepository) {
    suspend fun invoke(id: String): Boolean {
        return itemRepository.deleteNote(id)
    }
}