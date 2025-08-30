package com.example.tms_android_homework.note.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tms_android_homework.note.data.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Query("SELECT * FROM notes WHERE isDeleted = false")
    fun getAllEntities(): List<NoteEntity>?

    @Query("SELECT * FROM notes WHERE isNew = true")
    fun getNewNotes(): List<NoteEntity>?

    @Query("SELECT * FROM notes WHERE isUpdated = true")
    fun getUpdatedNotes(): List<NoteEntity>?

    @Query("SELECT * FROM notes WHERE isDeleted = true")
    fun getDeletedNotes(): List<NoteEntity>?

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNote(id: String): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<NoteEntity>)

    @Update
    fun updateEntity(entity: NoteEntity)

    @Update
    fun updateEntities(entities: List<NoteEntity>)

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteEntity(id: String)

    @Query("DELETE FROM notes WHERE id IN (:idList)")
    fun deleteEntities(idList: List<String>)

    @Query("SELECT COUNT(*) FROM notes")
    fun getNotesSize(): Int
}