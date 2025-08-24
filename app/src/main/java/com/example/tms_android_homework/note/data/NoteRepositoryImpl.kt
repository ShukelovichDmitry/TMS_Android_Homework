package com.example.tms_android_homework.note.data

import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val apiService: MockApiService)
    : NoteRepository {

    override suspend fun addNote(newNote: NoteDetailModel): Note? =
        withContext(Dispatchers.IO) {
            apiService.createPost(newNote)
        }

    override suspend fun getNotes(): List<Note>? =
        withContext(Dispatchers.IO) {
            try {
                apiService.fetchNotes()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    override suspend fun editNote(id: String, note: NoteDetailModel): Note? =
        withContext(Dispatchers.IO) {
            apiService.updateNote(id, note)
        }

    override suspend fun deleteNote(id: String): Boolean =
        withContext(Dispatchers.IO) {
            apiService.deleteNote(id).isSuccessful
        }
}