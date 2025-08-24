package com.example.tms_android_homework.note.domain.usecase

import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend fun invoke(id: String, title: String, description: String, imageUrl: String): Note? {
        val newNote = NoteDetailModel(title, description, imageUrl)
        return noteRepository.editNote(id, newNote)
    }
}