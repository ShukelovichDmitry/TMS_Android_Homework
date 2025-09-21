package com.example.tms_android_homework.note.domain.usecase

import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.NoteRepository
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    fun invoke(): Flowable<List<Note>> {
        return noteRepository.getNotes()
    }
}