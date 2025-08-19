package com.example.tms_android_homework.domain.usecase

import com.example.tms_android_homework.data.Note
import com.example.tms_android_homework.domain.NoteRepository
import com.example.tms_android_homework.domain.models.NoteDetailModel

class EditNoteUseCase(private val itemRepository: NoteRepository) {
    suspend fun invoke(id: String, title: String, description: String, imageUrl: String): Note? {
        val newNote = NoteDetailModel(title, description, imageUrl)
        return itemRepository.editNote(id, newNote)
    }
}