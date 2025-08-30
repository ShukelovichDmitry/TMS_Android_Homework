package com.example.tms_android_homework.note.domain.usecase

import com.example.tms_android_homework.note.domain.NoteRepository
import javax.inject.Inject

class SyncUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    suspend fun invoke(): Boolean {
        return noteRepository.sync()
    }
}