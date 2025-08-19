package com.example.tms_android_homework.domain.usecase

import com.example.tms_android_homework.data.Note
import com.example.tms_android_homework.domain.NoteRepository

class GetNotesUseCase(private val itemRepository: NoteRepository) {
    suspend fun invoke(): List<Note>? {
        return itemRepository.getNotes()
    }
}