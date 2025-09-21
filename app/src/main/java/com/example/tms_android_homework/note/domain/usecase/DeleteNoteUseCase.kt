package com.example.tms_android_homework.note.domain.usecase

import com.example.tms_android_homework.note.domain.NoteRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    fun invoke(id: String): Single<Boolean> {
        return noteRepository.deleteNote(id)
    }
}