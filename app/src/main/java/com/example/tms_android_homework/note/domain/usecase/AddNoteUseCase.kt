package com.example.tms_android_homework.note.domain.usecase

import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    fun invoke(title: String, descriptor: String, imageUrl: String): Single<Note> {
        return noteRepository.addNote(NoteDetailModel(title, descriptor, imageUrl))
    }
}