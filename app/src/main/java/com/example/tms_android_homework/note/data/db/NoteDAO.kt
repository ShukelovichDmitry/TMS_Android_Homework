package com.example.tms_android_homework.note.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tms_android_homework.note.data.Note
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Query("SELECT * FROM notes WHERE isDeleted = false")
    fun getAllEntities(): Flowable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE isNew = true OR isUpdated = true OR isDeleted = true")
    fun getNotSyncedNotes(): Single<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE isDeleted = true")
    fun getDeletedNotes(): Flowable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE isNew = true")
    fun getCreatedNotes(): Flowable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE isUpdated = true")
    fun getUpdatedNotes(): Flowable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNote(id: String): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<NoteEntity>)

    @Update
    fun updateEntity(entity: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteEntity(id: String)

    @Query("DELETE FROM notes WHERE id IN (:idList)")
    fun deleteEntities(idList: List<String>)

    @Query("UPDATE notes SET isNew = true AND isUpdated = true WHERE id IN (:idList)")
    fun updateEntities(idList: List<String>)

    @Query("SELECT MAX(id) FROM notes")
    fun getNotesLastId(): Int
}