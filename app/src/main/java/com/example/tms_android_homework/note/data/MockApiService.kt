package com.example.tms_android_homework.note.data

import com.example.tms_android_homework.note.domain.models.NoteDetailModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MockApiService {
    @GET("notes")
    fun fetchNotes(): Single<List<Note>>

    @DELETE("notes/{id}")
    fun deleteNote(@Path("id") noteId: String): Single<Response<Unit>>

    @PUT("notes/{id}")
    fun updateNote(@Path("id") noteId: String, @Body updatedNote: NoteDetailModel): Single<Note>

    @POST("notes")
    fun createPost(@Body createdNote: NoteDetailModel): Single<Note>

    @POST("notes")
    fun createPost(@Body createdNote: Note): Single<Note>
}