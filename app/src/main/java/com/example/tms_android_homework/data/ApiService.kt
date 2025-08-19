package com.example.tms_android_homework.data

import com.example.tms_android_homework.domain.models.NoteDetailModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("notes")
    suspend fun fetchNotes(): List<Note>?

    @DELETE("notes/{id}")
    suspend fun deleteNote(@Path("id") noteId: String): Response<Unit>

    @PUT("notes/{id}")
    suspend fun updateNote(@Path("id") noteId: String, @Body updatedNote: NoteDetailModel): Note?

    @POST("notes")
    suspend fun createPost(@Body createdNote: NoteDetailModel): Note?
}