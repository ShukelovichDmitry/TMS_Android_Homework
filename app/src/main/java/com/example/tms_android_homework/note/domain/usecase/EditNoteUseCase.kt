package com.example.tms_android_homework.note.domain.usecase

import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(private val noteRepository: NoteRepository) {
    fun invoke(id: String, title: String, description: String, imageUrl: String): Single<Note> {
        val newNote = NoteDetailModel(title, description, imageUrl)
        return noteRepository.editNote(id, newNote)
    }
}