package com.example.tms_android_homework.note.presentation.listeners

interface SaveNoteClickListener {
    fun onClick(id: String, title: String, description: String, imageUrl: String)
}