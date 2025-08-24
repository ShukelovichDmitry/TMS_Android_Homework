package com.example.tms_android_homework.note.domain.usecase

import com.example.tms_android_homework.note.domain.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend fun invoke(id: String): Boolean {
        return noteRepository.deleteNote(id)
    }
}