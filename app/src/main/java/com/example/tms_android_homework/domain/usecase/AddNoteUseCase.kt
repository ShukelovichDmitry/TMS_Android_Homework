package com.example.tms_android_homework.domain.usecase

import com.example.tms_android_homework.data.Note
import com.example.tms_android_homework.domain.NoteRepository
import com.example.tms_android_homework.domain.models.NoteDetailModel

class AddNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun invoke(title: String, descriptor: String, imageUrl: String): Note? {
        return noteRepository.addNote(NoteDetailModel(title, descriptor, imageUrl))
    }
}