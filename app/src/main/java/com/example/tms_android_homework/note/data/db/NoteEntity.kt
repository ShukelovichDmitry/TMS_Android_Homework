package com.example.tms_android_homework.note.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    //Запись создана в БД, но не сохранена на сервере
    var isNew: Boolean,
    //Запись обновлена в БД, но не сохранена на сервере
    var isUpdated: Boolean,
    //Запись удалена из БД, но не удалена с сервера
    var isDeleted: Boolean
) {
    override fun equals(other: Any?): Boolean {
        val otherNote = other as NoteEntity
        return  this.id == otherNote.id &&
                this.title.equals(otherNote.title) &&
                this.description.equals(otherNote.description) &&
                this.imageUrl.equals(otherNote.imageUrl) &&
                this.isNew == otherNote.isNew &&
                this.isUpdated == otherNote.isUpdated &&
                this.isDeleted == otherNote.isDeleted
    }
}