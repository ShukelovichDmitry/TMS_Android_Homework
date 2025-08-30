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
)