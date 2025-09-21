package com.example.tms_android_homework.note.domain

import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun addNote(newNote: NoteDetailModel): Single<Note>
    fun getNotes(): Flowable<List<Note>>
    fun editNote(id: String, note: NoteDetailModel): Single<Note>
    fun deleteNote(id: String): Single<Boolean>
    //Загрузить данные на сервер
    fun sync(): Single<Boolean>
}