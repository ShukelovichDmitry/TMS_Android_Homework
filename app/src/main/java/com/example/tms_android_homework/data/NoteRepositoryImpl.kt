package com.example.tms_android_homework.data

import com.example.tms_android_homework.domain.NoteRepository
import com.example.tms_android_homework.domain.models.NoteDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepositoryImpl(private val retrofitInstance: RetrofitInstance): NoteRepository {

    override suspend fun addNote(newNote: NoteDetailModel): Note? =
        withContext(Dispatchers.IO) {
            retrofitInstance.apiService.createPost(newNote)
        }

    override suspend fun getNotes(): List<Note>? =
        withContext(Dispatchers.IO) {
            try {
                retrofitInstance.apiService.fetchNotes()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    override suspend fun editNote(id: String, note: NoteDetailModel): Note? =
        withContext(Dispatchers.IO) {
            retrofitInstance.apiService.updateNote(id, note)
        }

    override suspend fun deleteNote(id: String): Boolean =
        withContext(Dispatchers.IO) {
            retrofitInstance.apiService.deleteNote(id).isSuccessful
        }
}