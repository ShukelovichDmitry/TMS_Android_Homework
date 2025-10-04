package com.example.tms_android_homework.note.data

data class Note(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String) {
    override fun equals(other: Any?): Boolean {
        val otherNote = other as Note
        return  this.id.equals(otherNote.id) &&
                this.title.equals(otherNote.title) &&
                this.description.equals(otherNote.description) &&
                this.imageUrl.equals(otherNote.imageUrl)
    }
}