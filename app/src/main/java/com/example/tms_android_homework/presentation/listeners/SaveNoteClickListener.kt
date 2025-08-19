package com.example.tms_android_homework.presentation.listeners

interface SaveNoteClickListener {
    fun onClick(id: String, title: String, descriptor: String, imageUrl: String)
}