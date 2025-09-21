package com.example.tms_android_homework.note.data

import com.example.tms_android_homework.note.data.db.NoteDAO
import com.example.tms_android_homework.note.data.db.NoteEntity
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val apiService: MockApiService,
    private val noteDAO: NoteDAO
): NoteRepository {

    private fun loadNotesFromServer(): Single<List<Note>> {
        return apiService.fetchNotes()
            .subscribeOn(Schedulers.io())
            .doOnSuccess { apiNoteList ->
                noteDAO.insertAll(apiNoteList.map { apiNote ->
                    NoteEntity(
                        id = apiNote.id,
                        title = apiNote.title,
                        description = apiNote.description,
                        imageUrl = apiNote.imageUrl,
                        isNew = false,
                        isUpdated = false,
                        isDeleted = false
                    )
                })
            }.onErrorReturn { emptyList() }
    }


    private fun getNotesFromDB(): Flowable<List<Note>> {
        return noteDAO.getAllEntities()
            .subscribeOn(Schedulers.io())
            .map { dbNotes ->
                dbNotes.map { dbNote ->
                    Note(
                        id = dbNote.id,
                        title = dbNote.title,
                        description = dbNote.description,
                        imageUrl = dbNote.imageUrl
                    )
                }
            }
    }


    override fun getNotes(): Flowable<List<Note>> {
        return getNotesFromDB()
            .flatMap { dbNoteList ->
                if (dbNoteList.isEmpty()) loadNotesFromServer().toFlowable()
                else Flowable.just(dbNoteList)
            }
    }

    override fun addNote(newNote: NoteDetailModel): Single<Note> {

        return apiService.createPost(newNote)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { apiNote ->
                noteDAO.insert(
                    NoteEntity(
                        id = apiNote.id,
                        title = apiNote.title,
                        description = apiNote.description,
                        imageUrl = apiNote.imageUrl,
                        isNew = false,
                        isUpdated = false,
                        isDeleted = false
                    )
                )
            }
            .doOnError {
                noteDAO.insert(
                    NoteEntity(
                        id = noteDAO.getNotesLastId().toString(),
                        title = newNote.title,
                        description = newNote.description,
                        imageUrl = newNote.imageUrl,
                        isNew = true,
                        isUpdated = false,
                        isDeleted = false
                    )
                )
            }
    }

    override fun editNote(id: String, note: NoteDetailModel): Single<Note> {
        return apiService.updateNote(id, note)
            .subscribeOn(Schedulers.io())
            .doOnError {
                noteDAO.updateEntity(
                    NoteEntity(
                        id = id,
                        title = note.title,
                        description = note.description,
                        imageUrl = note.imageUrl,
                        isNew = false,
                        isUpdated = true,
                        isDeleted = false
                    )
                )
            }.doOnSuccess {
                noteDAO.updateEntity(
                    NoteEntity(
                        id = id,
                        title = note.title,
                        description = note.description,
                        imageUrl = note.imageUrl,
                        isNew = false,
                        isUpdated = false,
                        isDeleted = false
                    )
                )
            }
    }

    override fun deleteNote(id: String): Single<Boolean> {

        return apiService.deleteNote(id)
            .subscribeOn(Schedulers.io())
            .map { apiResponse -> apiResponse.isSuccessful }
            .onErrorReturn { false }
            .doOnSuccess { isSuccessful ->
                if(isSuccessful)
                    noteDAO.deleteEntity(id)
                else
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
    }

    //Загрузить данные на сервер
    override fun sync(): Single<Boolean> {
        var forUpdate = mutableListOf<String>()
        var forDelete = mutableListOf<String>()

        return noteDAO.getNotSyncedNotes()
            .subscribeOn(Schedulers.io())
            .map { notSyncedNoteList ->
                notSyncedNoteList.map { notSyncedNote ->
                    if (notSyncedNote.isDeleted)
                        apiService.deleteNote(notSyncedNote.id)
                            .map { apiResponse -> apiResponse.isSuccessful }
                            .onErrorReturn { false }
                            .doOnSuccess {
                                if (it) forDelete.add(notSyncedNote.id)
                            }
                            .blockingGet()
                    else if (notSyncedNote.isUpdated)
                        apiService.updateNote(notSyncedNote.id, NoteDetailModel(
                            title = notSyncedNote.title,
                            description = notSyncedNote.description,
                            imageUrl = notSyncedNote.imageUrl
                        ))
                            .map { true }
                            .onErrorReturn { false }
                            .doOnSuccess { forUpdate.add(notSyncedNote.id) }
                            .blockingGet()
                    else
                        apiService.createPost(Note(
                            id = notSyncedNote.id,
                            title = notSyncedNote.title,
                            description = notSyncedNote.description,
                            imageUrl = notSyncedNote.imageUrl
                        ))
                            .map { true }
                            .onErrorReturn { false }
                            .doOnSuccess { forUpdate.add(notSyncedNote.id) }
                            .blockingGet()

                }.reduce { acc, cur -> acc && cur }
            }.doOnSuccess { isSynced ->
                if (isSynced) {
                    noteDAO.deleteEntities(forDelete)
                    noteDAO.updateEntities(forUpdate)
                }
            }
    }
}













