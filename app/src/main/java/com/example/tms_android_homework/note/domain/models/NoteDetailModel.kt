package com.example.tms_android_homework.note.domain.models

class NoteDetailModel(
    val title: String,
    val description: String,
    val imageUrl: String
) {
    override fun equals(other: Any?): Boolean {
        val otherNote = other as NoteDetailModel
        return  this.title.equals(otherNote.title) &&
                this.description.equals(otherNote.description) &&
                this.imageUrl.equals(otherNote.imageUrl)
    }
}