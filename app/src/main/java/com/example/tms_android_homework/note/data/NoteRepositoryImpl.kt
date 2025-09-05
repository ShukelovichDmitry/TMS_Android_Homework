package com.example.tms_android_homework.note.data

import com.example.tms_android_homework.note.data.db.NoteDAO
import com.example.tms_android_homework.note.data.db.NoteEntity
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val apiService: MockApiService,
    private val noteDAO: NoteDAO
): NoteRepository {

    override suspend fun getNotes(): List<Note> =
        withContext(Dispatchers.IO) {
            try {
                if (noteDAO.getNotesSize() == 0) {
                    val apiNotes = apiService.fetchNotes()
                    val noteEntities = apiNotes?.map { apiNote ->
                        NoteEntity(
                            id = apiNote.id,
                            title = apiNote.title,
                            description = apiNote.description,
                            imageUrl = apiNote.imageUrl,
                            isNew = false,
                            isUpdated = false,
                            isDeleted = false
                        )
                    }
                    noteEntities?.let {
                        noteDAO.insertAll(noteEntities)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            noteDAO.getAllEntities()?.map { entity ->
                Note(
                    id = entity.id,
                    title = entity.title,
                    description = entity.description,
                    imageUrl = entity.imageUrl
                )
            }.orEmpty()
        }

    override suspend fun addNote(newNote: NoteDetailModel): Note? =
        withContext(Dispatchers.IO) {
            var createdNote: Note? = null
            var newId = (noteDAO.getNotesSize() + 1).toString()
            try {
                createdNote = apiService.createPost(newNote)
                createdNote?.let { newId = it.id }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            noteDAO.insert(
                NoteEntity(
                    id = newId,
                    title = newNote.title,
                    description = newNote.description,
                    imageUrl = newNote.imageUrl,
                    isNew = createdNote == null,
                    isUpdated = false,
                    isDeleted = false
                )
            )
            createdNote
        }

    override suspend fun editNote(id: String, note: NoteDetailModel): Note? =
        withContext(Dispatchers.IO) {
            var updatedNote: Note? = null
            try {
                updatedNote = apiService.updateNote(id, note)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            noteDAO.updateEntity(
                NoteEntity(
                    id = id,
                    title = note.title,
                    description = note.description,
                    imageUrl = note.imageUrl,
                    isNew = false,
                    isUpdated = updatedNote == null,
                    isDeleted = false
                )
            )
            updatedNote
        }

    override suspend fun deleteNote(id: String): Boolean =
        withContext(Dispatchers.IO) {
            var result = false
            try {
                result = apiService.deleteNote(id).isSuccessful
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (result) {
                noteDAO.deleteEntity(id)
            } else {
                noteDAO.getNote(id)?.let { note ->
                    noteDAO.updateEntity(
                        NoteEntity(
                            id = id,
                            title = note.title,
                            description = note.description,
                            imageUrl = note.imageUrl,
                            isNew = false,
                            isUpdated = false,
                            isDeleted = true
                        )
                    )
                }
            }
            result
        }

    //Загрузить данные на сервер
    override suspend fun sync(): Boolean =
        withContext(Dispatchers.IO) {
            val forUpdate = mutableListOf<NoteEntity>()
            val forDelete = mutableListOf<String>()

            try {
                noteDAO.getNewNotes()?.forEach { newNote ->
                    apiService.createPost(
                        Note(
                            newNote.id,
                            newNote.title,
                            newNote.description,
                            newNote.imageUrl
                        )
                    )?.let {
                        newNote.isNew = false
                        forUpdate.add(newNote)
                    }
                }
                noteDAO.getUpdatedNotes()?.forEach { updatedNote ->
                    apiService.updateNote(updatedNote.id,
                        NoteDetailModel(
                            updatedNote.title,
                            updatedNote.description,
                            updatedNote.imageUrl
                        )
                    )?.let {
                        updatedNote.isUpdated = false
                        forUpdate.add(updatedNote)
                    }
                }
                noteDAO.getDeletedNotes()?.forEach { deletedNote ->
                    apiService.deleteNote(deletedNote.id).let {
                        if (it.isSuccessful) {
                            forDelete.add(deletedNote.id)
                        }
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                false
            }

            noteDAO.updateEntities(forUpdate)
            noteDAO.deleteEntities(forDelete)

            forUpdate.size > 0 || forDelete.size > 0

        }
}













